package com.example.symu_api.STOCK.Service;

import com.example.symu_api.AGENTS.Entity.AgentsEntity;
import com.example.symu_api.AGENTS.Repository.AgentsEntityRepo;
import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COMMON.Service.DBUtils;
import com.example.symu_api.CUSTOMER.Entity.CustomerEntity;
import com.example.symu_api.CUSTOMER.Repository.CustomerRepository;
import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import com.example.symu_api.RECEIPT.Repository.ReceiptRepository;
import com.example.symu_api.STOCK.Dto.StockCloseSaleDto;
import com.example.symu_api.STOCK.Dto.StockPostSaleDto;
import com.example.symu_api.STOCK.Dto.StockPriceDto;
import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Model.StockDetailsRes;
import com.example.symu_api.STOCK.Model.StockEntityModel;
import com.example.symu_api.STOCK.Model.StockRes;
import com.example.symu_api.STOCK.Repository.StockEntityModelRepo;
import com.example.symu_api.STOCK.Repository.StockEntityRepo;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchRepo;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Repository.StockModelRepo;
import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockEntityRepo stockEntityRepo;
    @Autowired
    private StockEntityModelRepo stockEntityModelRepo;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AgentsEntityRepo agentsEntityRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private StockModelRepo stockModelRepo;
    @Autowired
    private StockBatchRepo stockBatchRepo;

    @Override
    public SymuResponse createOrUpdateStock(StockEntity stock) {
        SymuResponse symuResponse = new SymuResponse<>();
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        StockEntity stockEntity = new StockEntity();
        try {
            try {
                StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stock.getCode());
                stockEntity.setCode(stockEntityData.getCode());
                stockEntity.setStockUpdatedOn(timestamp);
                stockEntity.setStockUpdatedBy(stock.getStockUpdatedBy());
                stockEntity.setStockStatusCode(stock.getStockStatusCode());
                stockEntity.setStockCreatedOn(stockEntityData.getStockCreatedOn());
                stockEntity.setStockCreatedBy(stockEntityData.getStockCreatedBy());
            } catch (Exception e) {
                // new stock
                stockEntity.setStockCreatedOn(timestamp);
                stockEntity.setStockCreatedBy(stock.getStockCreatedBy());
                stockEntity.setStockStatusCode(stock.getStockStatusCode());
            }
            StockModelEntity stockModelEntity = stockModelRepo.getStockModelEntitiesByCode(stock.getStockModelCode());
            StockBatchEntity stockBatchEntity = stockBatchRepo.getStockBatchEntitiesByCode(stock.getStockBatchCode());
            stockEntity.setStockCompanyCode(stock.getStockCompanyCode());
            stockEntity.setStockCountryCode(stock.getStockCountryCode());
            stockEntity.setStockRegionCode(stock.getStockRegionCode());
            stockEntity.setStockBranchCode(stock.getStockBranchCode());
            stockEntity.setStockBatchCode(stock.getStockBatchCode());
            stockEntity.setStockAgnCode(stock.getStockAgnCode());
            stockEntity.setStockImei(stock.getStockImei());
            stockEntity.setStockModelCode(stock.getStockModelCode());
            stockEntity.setStockMemory(stock.getStockMemory());
            stockEntity.setStockBuyingPrice(stockBatchEntity.getBatchBuyingPrice());
            stockEntity.setStockSellingPrice(stockModelEntity.getModelSellingPrice());
            stockEntity.setStockProfit(stockModelEntity.getModelSellingPrice() - stockBatchEntity.getBatchBuyingPrice());
            stockEntity.setStockDefaulted("N");
            stockEntity.setStockBaseCurrency(stock.getStockBaseCurrency());
            stockEntity.setStockSoldBy(stock.getStockSoldBy());
            stockEntity.setStockTradeName(stock.getStockTradeName());
            stockEntity.setStockDealerCode(stock.getStockDealerCode());
            StockEntity stockEntitySaved = stockEntityRepo.save(stockEntity);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockEntitySaved);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockEntityByStockCode(int stockCode) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            StockEntityModel stockEntity = stockEntityModelRepo.getStockEntitiesByCode(stockCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(stockEntity);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Error occurred while fetching stock");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockByBranchAndStatus(int brnCode, int stockStatusCode) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            List<StockEntityModel> stockEntityList = stockEntityModelRepo.getStockEntitiesByStockBranchCodeAndStockStatusCode(brnCode, stockStatusCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(stockEntityList);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Error occurred while fetching stock");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockEntitiesByStockCompanyCode(int companyCode) {
        SymuResponse symuResponse = new SymuResponse();
        Connection conn = null;
        CallableStatement cst = null;
        String sql = "SELECT \n" +
                "      stock_code,\n" +
                "      stock_comp_code,\n" +
                "      stock_country_code,\n" +
                "      stock_region_code,\n" +
                "      stock_brn_code,\n" +
                "      stock_batch_code,\n" +
                "      stock_agn_code,\n" +
                "      stock_imei,\n" +
                "      stock_model_code,\n" +
                "      stock_memory,\n" +
                "      stock_buying_price,\n" +
                "      stock_selling_price,\n" +
                "      stock_profit,\n" +
                "      stock_status_code,\n" +
                "      stock_base_currency,\n" +
                "      stock_defaulted,\n" +
                "      stock_customer_code,\n" +
                "      stock_created_on,\n" +
                "      stock_updated_on,\n" +
                "      stock_created_by,\n" +
                "      stock_updated_by,\n" +
                "      stock_sold_by,\n" +
                "      stock_trade_name,\n" +
                "      stock_dealer_code,\n" +
                "      status_description,\n" +
                "      status_name,\n" +
                "      status_short_desc,\n" +
                "      brn_name,\n" +
                "      countries.ctry_name,\n" +
                "      batch_no\n" +
                "FROM stock,stock_status,branches,countries,stock_batch\n" +
                "where stock_status.status_code=stock_status_code\n" +
                "and BRN_CODE=stock_brn_code\n" +
                "and ctry_code=stock_country_code\n" +
                "and batch_code=stock_batch_code\n" +
                "and stock_comp_code=1";
        try {
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<StockRes> stockResList = new ArrayList<StockRes>();
            //    List<StockEntityModel> stockEntityList = stockEntityModelRepo.getStockEntitiesByStockCompanyCode(companyCode);

            while (rs.next()) {
                StockRes stockRes = new StockRes();
                stockRes.setCode(rs.getInt("stock_code"));
                stockRes.setStockCompanyCode(rs.getInt("stock_comp_code"));
                stockRes.setStockCountryCode(rs.getInt("stock_country_code"));
                stockRes.setStockRegionCode(rs.getInt("stock_region_code"));
                stockRes.setStockBranchCode(rs.getInt("stock_brn_code"));
                stockRes.setStockBatchCode(rs.getInt("stock_batch_code"));
                stockRes.setStockAgnCode(rs.getInt("stock_agn_code"));
                stockRes.setStockImei(rs.getString("stock_imei"));
                stockRes.setStockModelCode(rs.getInt("stock_model_code"));
                stockRes.setStockMemory(rs.getString("stock_memory"));
                stockRes.setStockBuyingPrice(rs.getDouble("stock_buying_price"));
                stockRes.setStockSellingPrice(rs.getDouble("stock_selling_price"));
                stockRes.setStockProfit(rs.getDouble("stock_profit"));
                stockRes.setStockStatusCode(rs.getInt("stock_status_code"));
                stockRes.setStockBaseCurrency(rs.getString("stock_base_currency"));
                stockRes.setStockDefaulted(rs.getString("stock_defaulted"));
                stockRes.setStockCustomerCode(rs.getInt("stock_customer_code"));
                stockRes.setStockCreatedOn(rs.getString("stock_created_on"));
                stockRes.setStockUpdatedOn(rs.getString("stock_updated_on"));
                stockRes.setStockCreatedBy(rs.getString("stock_created_by"));
                stockRes.setStockUpdatedBy(rs.getString("stock_updated_by"));
                stockRes.setStockSoldBy(rs.getString("stock_sold_by"));
                stockRes.setStockTradeName(rs.getString("stock_trade_name"));
                stockRes.setStockDealerCode(rs.getInt("stock_dealer_code"));
                stockRes.setStockStatusDescription(rs.getString("status_description"));
                stockRes.setStockStatusName(rs.getString("status_name"));
                stockRes.setStatusShortDesc(rs.getString("status_short_desc"));
                stockRes.setStockBranchName(rs.getString("brn_name"));
                stockRes.setStockCountryName(rs.getString("ctry_name"));
                stockRes.setStockBatchNumber(rs.getString("batch_no"));
                stockResList.add(stockRes);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(stockResList);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Error occurred while fetching stock");
            symuResponse.setData(e.getMessage());
        } finally {
            DBUtils.CloseConnections(null, cst, conn);
        }
        return symuResponse;
    }

    @Override
    public SymuResponse updateStockPrice(StockPriceDto stockPriceDto) {
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        SymuResponse symuResponse = new SymuResponse();
        try {
            StockEntity stockEntity = stockEntityRepo.getStockEntitiesByCode(stockPriceDto.getStockCode());
            stockEntity.setStockBuyingPrice(stockPriceDto.getBuyingPrice());
            stockEntity.setStockSellingPrice(stockPriceDto.getSellingPrice());
            stockEntity.setStockUpdatedBy(stockPriceDto.getUserCode());
            stockEntity.setStockProfit(stockPriceDto.getSellingPrice() - stockPriceDto.getBuyingPrice());
            stockEntity.setStockUpdatedOn(timestamp);
            stockEntity.setStockStatusCode(stockPriceDto.getStatusCode());
            //stockEntity.setStockCustomerCode(0);
            StockEntity saved = stockEntityRepo.save(stockEntity);
            if (saved.getStockBuyingPrice() > 0) {
                symuResponse.setStatusCode("0");
                symuResponse.setMessage("success");
                symuResponse.setData("Stock buying price updated successfully");
            }
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse stockPostSale(StockPostSaleDto stockPostSaleDto) {
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        SymuResponse symuResponse = new SymuResponse<>();
        // create or update customer
        try {
            CustomerEntity customerEntity = new CustomerEntity();
            StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockPostSaleDto.getStockCode());
            UserEntity userEntity = userRepository.getAllByCode(stockPostSaleDto.getUserCode());
            try {
                CustomerEntity customerEntityData = customerRepository.getCustomerEntitiesByCustomerPhoneNumber(
                        stockPostSaleDto.getCustomerPhoneNumber());
                customerEntity.setCustomerCode(customerEntityData.getCustomerCode());
            } catch (Exception e) {
                // new customer
            }
            customerEntity.setCustomerCompanyCode(stockEntityData.getStockCompanyCode());
            customerEntity.setCustomerCountryCode(stockEntityData.getStockCountryCode());
            customerEntity.setCustomerRegionCode(stockEntityData.getStockRegionCode());
            customerEntity.setCustomerBranchCode(stockEntityData.getStockBranchCode());
            customerEntity.setCustomerName(stockPostSaleDto.getCustomerName());
            customerEntity.setCustomerPhoneNumber(stockPostSaleDto.getCustomerPhoneNumber());
            customerEntity.setCustomerNationalId(stockPostSaleDto.getCustomerNationalId());
            CustomerEntity customerEntitysaved = customerRepository.save(customerEntity);
            int customerCode = customerEntitysaved.getCustomerCode();

            //post sale
            stockEntityData.setStockStatusCode(stockPostSaleDto.getNextStatusCode());
            stockEntityData.setStockUpdatedBy(stockPostSaleDto.getUserCode());
            stockEntityData.setStockTradeName(stockPostSaleDto.getTradingName());
            stockEntityData.setStockDealerCode(stockPostSaleDto.getStockDealerCode());
            stockEntityData.setStockCustomerCode(customerCode);
            StockEntity saved = stockEntityRepo.save(stockEntityData);
            if (saved != null) {
                //saved
            }
            //post Receipt
            Integer receiptNo;
            try {
                receiptNo = receiptRepository.findMaxCode() + 1;
            } catch (Exception e) {
                receiptNo = 1;
            }
            receiptNo = receiptRepository.findMaxCode() + 1;
            ReceiptEntity receiptEntity = new ReceiptEntity();
            receiptEntity.setReceiptCompanyCode(stockEntityData.getStockCompanyCode());
            receiptEntity.setReceiptCountryCode(stockEntityData.getStockCountryCode());
            receiptEntity.setReceiptRegionCode(stockEntityData.getStockRegionCode());
            receiptEntity.setReceiptBranchCode(stockEntityData.getStockBranchCode());
            receiptEntity.setReceiptNo(String.valueOf(receiptNo));
            receiptEntity.setReceiptCreatedOn(timestamp);
            receiptEntity.setReceiptCreatedBy(userEntity.getUserFirstName() + " " + userEntity.getUserLastName());
            receiptEntity.setReceiptUpdatedOn(timestamp);
            receiptEntity.setReceiptCustomerIdNo(customerEntitysaved.getCustomerNationalId());
            receiptEntity.setReceiptCustomerPhoneNo(customerEntitysaved.getCustomerPhoneNumber());
            receiptEntity.setReceiptCustomerName(customerEntitysaved.getCustomerName());
            receiptEntity.setReceiptStockCode(stockPostSaleDto.getStockCode());
            receiptEntity.setReceiStockImei(stockEntityData.getStockImei());
            receiptEntity.setReceiStockQuantity(1);
            receiptEntity.setReceiptAmount(stockEntityData.getStockSellingPrice());
            receiptEntity.setReceiptModel(stockEntityData.getStockMemory());
            receiptEntity.setReceiptStatus("POSTED");
            receiptEntity.setReceiptDealership(stockPostSaleDto.getTradingName());
            ReceiptEntity rctSaved = receiptRepository.save(receiptEntity);
            if (rctSaved != null) {
                //saved
            }

            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(rctSaved);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse stockRejectPostedSale(int stockCode, int stockUserCode) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockCode);
            stockEntityData.setStockCustomerCode(null);
            stockEntityData.setStockAgnCode(null);
            stockEntityData.setStockStatusCode(2);
            StockEntity updated = stockEntityRepo.save(stockEntityData);

            //reject receipt
            UserEntity userEntity = userRepository.getAllByCode(stockUserCode);
            ReceiptEntity receiptEntityData = receiptRepository.getAllByReceiptStockCodeAndReceiptStatus(
                    updated.getCode(), "POSTED"
            );
            receiptEntityData.setReceiptStatus("REJECTED");
            receiptEntityData.setReceiptUpdatedBy(userEntity.getUserFirstName() + " " + userEntity.getUserLastName());
            ReceiptEntity rejected = receiptRepository.save(receiptEntityData);
            if (rejected.getReceiptStatus().equals("REJECTED")) {
                symuResponse.setStatusCode("0");
                symuResponse.setMessage("success");
                symuResponse.setData("Posted sale has been rejected successfully");
            }
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse stockCloseSale(StockCloseSaleDto stockCloseSaleDto) {
        SymuResponse symuResponse = new SymuResponse();
        // create or update agent
        try {
            AgentsEntity agentsEntity = new AgentsEntity();
            StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockCloseSaleDto.getStockCode());
            try {
                AgentsEntity agentsEntityData = agentsEntityRepo.getAgentsEntitiesByAgentPhoneNumber(
                        stockCloseSaleDto.getAgentPhoneNumber());
                agentsEntity.setAgentCode(agentsEntityData.getAgentCode());
            } catch (Exception e) {
                //new agent
            }
            agentsEntity.setAgentCompanyCode(stockEntityData.getStockCompanyCode());
            agentsEntity.setAgentCountryCode(stockEntityData.getStockCountryCode());
            agentsEntity.setAgentRegionCode(stockEntityData.getStockRegionCode());
            agentsEntity.setAgentBranchCode(stockEntityData.getStockBranchCode());
            agentsEntity.setAgentName(stockCloseSaleDto.getAgentName());
            agentsEntity.setAgentPhoneNumber(stockCloseSaleDto.getAgentPhoneNumber());
            agentsEntity.setAgentNationalId(stockCloseSaleDto.getAgentNationalId());
            AgentsEntity agentsEntitySaved = agentsEntityRepo.save(agentsEntity);
            int agentCode = agentsEntitySaved.getAgentCode();

            // close sale
            stockEntityData.setStockStatusCode(stockCloseSaleDto.getNextStatusCode());
            stockEntityData.setStockUpdatedBy(stockCloseSaleDto.getUserCode());
            stockEntityData.setStockAgnCode(agentCode);
            StockEntity saved = stockEntityRepo.save(stockEntityData);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(saved);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse updateDefaultStatus(int stockCode, String defaultStatus) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockCode);
            stockEntityData.setStockDefaulted(defaultStatus);
            StockEntity updated = stockEntityRepo.save(stockEntityData);
            if (updated != null) {
                symuResponse.setStatusCode("0");
                symuResponse.setMessage("success");
                symuResponse.setData("Default status updated");
            }
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    public SymuResponse getAllStockDetails(int companyCode) {
        SymuResponse symuResponse = new SymuResponse();
        Connection conn = null;
        CallableStatement cst = null;
        String sql = "SELECT stock_code,stock_imei,stock_selling_price,stock_defaulted," +
                "ctry_currency_code,stock_brn_code,stock_region_code,stock_country_code,\n" +
                "       model_name,stock_created_on,stock_updated_on,stock_updated_on,\n" +
                "       customer_name,customer_phone,customer_national_id,\n" +
                "       agent_name,\n" +
                "       brn_name,\n" +
                "       dealer_name\n" +
                "FROM stock,stock_model,customer,agents,branches,dealership,countries\n" +
                "WHERE stock_model_code=model_code\n" +
                "  and stock_customer_code=customer_code\n" +
                "  and stock_agn_code=agent_code\n" +
                "  and stock_brn_code=BRN_CODE\n" +
                "  and stock_dealer_code=dealer_code\n" +
                "  and stock_country_code=ctry_code\n" +
                "  and stock_status_code=4\n" +
                "  AND stock_comp_code=1";
        try {
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<StockDetailsRes> stockDetailsResList = new ArrayList<StockDetailsRes>();
            while (rs.next()) {
                StockDetailsRes stockDetailsRes = new StockDetailsRes();
                stockDetailsRes.setStockCode(rs.getInt("stock_code"));
                stockDetailsRes.setStockImei(rs.getString("stock_imei"));
                stockDetailsRes.setStockSellingPrice(rs.getDouble("stock_selling_price"));
                stockDetailsRes.setStockDefaulted(rs.getString("stock_defaulted"));
                stockDetailsRes.setStockModelName(rs.getString("model_name"));
                stockDetailsRes.setStockCustomerName(rs.getString("customer_name"));
                stockDetailsRes.setStockAgentName(rs.getString("agent_name"));
                stockDetailsRes.setStockBranchName(rs.getString("brn_name"));
                stockDetailsRes.setStockDealerShipName(rs.getString("dealer_name"));
                stockDetailsRes.setStockCurrencyCode(rs.getString("ctry_currency_code"));
                stockDetailsRes.setStockCustomerPhone(rs.getString("customer_phone"));
                stockDetailsRes.setStockCustomerNationalId(rs.getString("customer_national_id"));
                stockDetailsRes.setStockBranchCode(rs.getString("stock_brn_code"));
                stockDetailsRes.setStockRegionCode(rs.getString("stock_region_code"));
                stockDetailsRes.setStockCountryCode(rs.getString("stock_country_code"));
                stockDetailsRes.setStockCreatedOn(rs.getString("stock_updated_on"));
                stockDetailsRes.setStockUpdatedOn(rs.getString("stock_updated_on"));
                stockDetailsResList.add(stockDetailsRes);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(stockDetailsResList);
            DBUtils.CloseConnections(null, cst, conn);
        } catch (Exception ex) {
            DBUtils.CloseConnections(null, cst, conn);
            ex.printStackTrace();
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(ex.getMessage());

        } finally {
            DBUtils.CloseConnections(null, cst, conn);
        }
        return symuResponse;
    }

}
