package com.example.symu_api.STOCK.Service;

import com.example.symu_api.AGENTS.Entity.AgentsEntity;
import com.example.symu_api.AGENTS.Repository.AgentsEntityRepo;
import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Repository.BranchRepository;
import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import com.example.symu_api.CLUSTER.Repository.ClusterEntityRepository;
import com.example.symu_api.COMMON.Model.SymuBulkResponse;
import com.example.symu_api.COMMON.Model.SymuErrorInfo;
import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COMMON.Service.CommonUtils;
import com.example.symu_api.COMMON.Service.DBUtils;
import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import com.example.symu_api.COUNTRY.Repository.CountryRepository;
import com.example.symu_api.CUSTOMER.Entity.CustomerEntity;
import com.example.symu_api.CUSTOMER.Repository.CustomerRepository;
import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import com.example.symu_api.RECEIPT.Repository.ReceiptRepository;
import com.example.symu_api.ROLE.Entity.RoleEntity;
import com.example.symu_api.ROLE.Repository.RoleRepository;
import com.example.symu_api.STOCK.Dto.*;
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
import com.example.symu_api.STOCK_STATUS.entity.StockStatusEntity;
import com.example.symu_api.STOCK_STATUS.repository.StockStatusRepository;
import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StockStatusRepository stockStatusRepository;
    @Autowired
    private ClusterEntityRepository clusterEntityRepository;

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
                stockEntity.setStockCreatedBy(stockEntityData.getStockCreatedBy());
                if (stockEntityData.getStockRegionCode() != stock.getStockRegionCode() ||
                        stockEntityData.getStockBranchCode() != stock.getStockBranchCode() ||
                        stockEntityData.getStockClusterCode() != stock.getStockClusterCode()) {
                    stockEntity.setStockCreatedOn(timestamp);
                } else {
                    stockEntity.setStockCreatedOn(stockEntityData.getStockCreatedOn());
                }

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
            stockEntity.setStockClusterCode(stock.getStockClusterCode());
            StockEntity stockEntitySaved = stockEntityRepo.save(stockEntity);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockEntitySaved);
        } catch (
                Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuBulkResponse createStockBulk(CreateStockBulkDto createStockBulkDto) {
        SymuBulkResponse symuBulkResponse = new SymuBulkResponse<>();
        List<SymuErrorInfo> symuErrorInfoList = new ArrayList<>();
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        int success = 0;
        int failed = 0;
        ClusterEntity clusterEntity = clusterEntityRepository.getClusterEntitiesByCode(createStockBulkDto.getStockClusterCode());
        StockModelEntity stockModelEntity = stockModelRepo.getStockModelEntitiesByCode(createStockBulkDto.getStockModelCode());
        StockBatchEntity stockBatchEntity = stockBatchRepo.getStockBatchEntitiesByCode(createStockBulkDto.getStockBatchCode());
        CountryEntity countryEntity = countryRepository.getCountryEntitiesByCode(clusterEntity.getClusterCountryCode());
        List<StockEntity> stockEntityList = new ArrayList<>();
        for (String stockImei : createStockBulkDto.getStockImei()) {
            StockEntity stockEntity = new StockEntity();
            try {
                stockEntity.setStockCompanyCode(clusterEntity.getCompanyCode());
                stockEntity.setStockCountryCode(clusterEntity.getClusterCountryCode());
                stockEntity.setStockRegionCode(clusterEntity.getClusterRegionCode());
                stockEntity.setStockBranchCode(clusterEntity.getClusterBranchCode());
                stockEntity.setStockClusterCode(createStockBulkDto.getStockClusterCode());
                stockEntity.setStockBatchCode(createStockBulkDto.getStockBatchCode());
                stockEntity.setStockImei(stockImei);
                stockEntity.setStockModelCode(createStockBulkDto.getStockModelCode());
                stockEntity.setStockMemory(stockModelEntity.getModelName());
                stockEntity.setStockBuyingPrice(stockBatchEntity.getBatchBuyingPrice());
                stockEntity.setStockSellingPrice(stockModelEntity.getModelSellingPrice());
                stockEntity.setStockProfit(stockModelEntity.getModelSellingPrice() - stockBatchEntity.getBatchBuyingPrice());
                stockEntity.setStockDefaulted("N");
                stockEntity.setStockBaseCurrency(countryEntity.getCountryCurrencyCode());
                stockEntity.setStockCreatedOn(timestamp);
                stockEntity.setStockCreatedBy(createStockBulkDto.getStockCreatedBy());
                stockEntity.setStockStatusCode(1);
                stockEntity.setStockUpdatedOn(timestamp);
                stockEntity.setStockUpdatedBy(1);
                StockEntity stockEntitySaved = stockEntityRepo.save(stockEntity);
                if (stockEntitySaved.getStockImei() != null) {
                    // saved
                    stockEntityList.add(stockEntitySaved);
                    success = success + 1;
                }
            } catch (Exception e) {
                String errorMsg = e.getMessage();
                if (errorMsg.contains("Duplicate")) {
                    errorMsg = "Duplicate entry for the imei";
                } else {
                    errorMsg = e.getMessage();
                }
                failed = failed + 1;
                SymuErrorInfo symuErrorInfo = new SymuErrorInfo();
                symuErrorInfo.setStatusCode("1");
                symuErrorInfo.setStatusDesc(stockImei);
                symuErrorInfo.setStatusMessage(errorMsg);
                symuErrorInfoList.add(symuErrorInfo);
            }
        }
        // prepare response
        symuBulkResponse.setStatusCode("0");
        symuBulkResponse.setMessage("Success");
        symuBulkResponse.setSuccess(String.valueOf(success));
        symuBulkResponse.setFailed(String.valueOf(failed));
        symuBulkResponse.setData(stockEntityList);
        symuBulkResponse.setSymuErrorInfoList(symuErrorInfoList);

        return symuBulkResponse;
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
    public SymuResponse getStockEntitiesByStockCompanyCode(GetAllStockDto getAllStockDto,
                                                           Pageable pageable) {
        SymuResponse symuResponse = new SymuResponse();
        Connection conn = null;
        CallableStatement cst = null;
        String sql = "SELECT \n" +
                "                      stock_code,\n" +
                "                      stock_comp_code,\n" +
                "                      stock_country_code,\n" +
                "                      stock_region_code,\n" +
                "                      stock_brn_code,\n" +
                "                      stock_batch_code,\n" +
                "                      stock_agn_code,\n" +
                "                      stock_imei,\n" +
                "                      stock_model_code,\n" +
                "                      stock_memory,\n" +
                "                      stock_buying_price,\n" +
                "                      stock_selling_price,\n" +
                "                      stock_profit,\n" +
                "                      stock_status_code,\n" +
                "                      stock_base_currency,\n" +
                "                      stock_defaulted,\n" +
                "                      stock_customer_code,\n" +
                "                      stock_created_on,\n" +
                "                      stock_updated_on,\n" +
                "                      stock_created_by,\n" +
                "                      stock_updated_by,\n" +
                "                      stock_sold_by,\n" +
                "                      stock_trade_name,\n" +
                "                      stock_dealer_code,\n" +
                "                      status_description,\n" +
                "                      status_name,\n" +
                "                      status_short_desc,\n" +
                "                      brn_name,\n" +
                "                      countries.ctry_name,\n" +
                "                      batch_no,\n" +
                "                      cluster_code,\n" +
                "                      cluster_name\n" +
                "                FROM stock,stock_status,branches,countries,stock_batch,cluster\n" +
                "                where stock_status.status_code=stock_status_code\n" +
                "                and BRN_CODE=stock_brn_code\n" +
                "                and ctry_code=stock_country_code\n" +
                "                and batch_code=stock_batch_code\n" +
                "                and stock_cluster_code=cluster_code\n" +
                "                and status_short_desc='v_status_short_desc'\n" +
                "                and stock_comp_code=v_stock_comp_code\n" +
                "                and stock_country_code=ifnull(v_stock_country_code,stock_country_code)\n" +
                "                and stock_region_code=ifnull(v_stock_region_code,stock_region_code)\n" +
                "                and stock_brn_code=ifnull(v_stock_brn_code,stock_brn_code)\n" +
                "                and stock_cluster_code=ifnull(v_stock_cluster_code,stock_cluster_code)\n" +
                "                and stock_batch_code=ifnull(v_stock_batch_code,stock_batch_code)\n" +
                "                order by stock_updated_on desc";
        try {
            sql = sql.replace("v_status_short_desc", getAllStockDto.getStatusShortDesc());
            sql = sql.replace("v_stock_comp_code", getAllStockDto.getCompanyCode());
            sql = sql.replace("v_stock_country_code", getAllStockDto.getStockCountryCode());
            sql = sql.replace("v_stock_region_code", getAllStockDto.getStockRegionCode());
            sql = sql.replace("v_stock_brn_code", getAllStockDto.getStockBranchCode());
            sql = sql.replace("v_stock_cluster_code", getAllStockDto.getStockClusterCode());
            sql = sql.replace("v_stock_batch_code", getAllStockDto.getStockBatchCode());
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<StockRes> stockResList = new ArrayList<>();

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
                stockRes.setStockClusterCode(rs.getInt("cluster_code"));
                stockRes.setStockClusterName(rs.getString("cluster_name"));
                stockResList.add(stockRes);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            Page<StockRes> stockResPage = CommonUtils.pageData(stockResList, pageable);
            symuResponse.setData(stockResPage);
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
            StockModelEntity stockModelEntity = stockModelRepo.getStockModelEntitiesByCode(stockEntityData.getStockModelCode());

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
            stockEntityData.setStockSellingPrice(stockModelEntity.getModelSellingPrice());
            stockEntityData.setStockProfit(stockModelEntity.getModelSellingPrice() - stockEntityData.getStockBuyingPrice());
            StockEntity saved = stockEntityRepo.save(stockEntityData);
            if (saved != null) {
                //saved
            }
            //post Receipt
            int receiptNo;
            try {
                receiptNo = receiptRepository.findMaxCode() + 1;
            } catch (Exception e) {
                receiptNo = 1;
            }
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
            receiptEntity.setReceiptAmount(saved.getStockSellingPrice());
            receiptEntity.setReceiptModel(stockEntityData.getStockMemory());
            receiptEntity.setReceiptStatus("POSTED");
            receiptEntity.setReceiptDealership(stockPostSaleDto.getTradingName());
            receiptEntity.setReceiptClusterCode(stockEntityData.getStockClusterCode());
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
    public SymuResponse stockApproval(StockApprovalDto stockApprovalDto) {
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        SymuResponse symuResponse = new SymuResponse<>();
        List<SymuErrorInfo> symuErrorInfoList = new ArrayList<>();
        for (int stockCode : stockApprovalDto.getStockCode()) {
            SymuErrorInfo symuErrorInfo = new SymuErrorInfo();
            try {
                StockEntity stockEntity = stockEntityRepo.getStockEntitiesByCode(stockCode);
                StockStatusEntity stockStatusEntity = stockStatusRepository.getStockStatusEntitiesByStatusCode(
                        stockApprovalDto.getNextStatusCode());
                if (stockStatusEntity.getStatusShortDesc().equalsIgnoreCase("DELETED")) {
                    stockEntity.setStockImei(timestamp + "_" + stockEntity.getStockImei());
                }
                stockEntity.setStockStatusCode(stockApprovalDto.getNextStatusCode());
                stockEntity.setStockUpdatedBy(stockApprovalDto.getUserCode());
                stockEntity.setStockUpdatedOn(timestamp);
                StockEntity saved = stockEntityRepo.save(stockEntity);
                if (saved != null) {
                    //updated
                }
                symuResponse.setStatusCode("0");
                symuResponse.setMessage("success");
            } catch (Exception e) {
                symuErrorInfo.setStatusCode("1");
                symuErrorInfo.setStatusDesc("StockCode : " + stockApprovalDto.getStockCode());
                symuErrorInfo.setStatusMessage(e.getMessage());
                symuErrorInfoList.add(symuErrorInfo);
                symuResponse.setData(symuErrorInfoList);
                System.out.println("symuErrorInfoList : " + symuErrorInfoList);
            }
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
//save update
            if (updated != null) {
                // stock status updated
            }
            //reject receipt
            List<ReceiptEntity> receiptEntityData = receiptRepository.getAllByReceiptStockCode(
                    updated.getCode());
            for (ReceiptEntity receiptEntity : receiptEntityData) {
                receiptRepository.delete(receiptEntity);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData("Posted sale has been rejected successfully");
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse stockCloseSale(StockCloseSaleDto stockCloseSaleDto) {
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
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

            //update receipt date
            List<ReceiptEntity> receiptEntityData = receiptRepository.getAllByReceiptStockCode(stockEntityData.getCode());
            for (ReceiptEntity receiptEntity : receiptEntityData) {
                receiptEntity.setReceiptUpdatedBy(String.valueOf(stockCloseSaleDto.getUserCode()));
                receiptEntity.setReceiptUpdatedOn(timestamp);
                ReceiptEntity receipt = receiptRepository.save(receiptEntity);
                if (receipt != null) {
                    //saved
                }
            }
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

    public SymuResponse getAllStockDetails(StockDetailsDto stockDetailsDto, Pageable pageable) {
        SymuResponse symuResponse = new SymuResponse();
        Connection conn = null;
        CallableStatement cst = null;
        String sql = "SELECT stock_code,\n" +
                "       stock_imei,\n" +
                "       stock_selling_price,\n" +
                "       stock_defaulted,\n" +
                "       ctry_currency_code,\n" +
                "       stock_brn_code,\n" +
                "       stock_region_code,\n" +
                "       stock_country_code,\n" +
                "       model_name,\n" +
                "       stock_created_on,\n" +
                "       rct_updated_on,\n" +
                "       customer_name,\n" +
                "       customer_phone,\n" +
                "       customer_national_id,\n" +
                "       agent_name,\n" +
                "       brn_name,\n" +
                "       dealer_name,\n" +
                "       batch_no,\n" +
                "       cluster_code,\n" +
                "       cluster_name\n" +
                "FROM stock,\n" +
                "     stock_model,\n" +
                "     customer,\n" +
                "     agents,\n" +
                "     branches,\n" +
                "     dealership,\n" +
                "     countries,\n" +
                "     receipts,\n" +
                "     stock_batch,\n" +
                "     cluster\n" +
                "WHERE stock_model_code = model_code\n" +
                "  and rct_stock_code = stock_code\n" +
                "  and rct_status = 'POSTED'\n" +
                "  and stock_customer_code = customer_code\n" +
                "  and stock_agn_code = agent_code\n" +
                "  and stock_brn_code = BRN_CODE\n" +
                "  and stock_dealer_code = dealer_code\n" +
                "  and stock_country_code = ctry_code\n" +
                "  and stock_batch_code = batch_code\n" +
                "  and stock_cluster_code = cluster_code\n" +
                "  and cluster_code = rct_cluster_code\n" +
                "  and stock_status_code = 4\n" +
                "  AND DATE(rct_updated_on)\n" +
                "    between ifnull('v_rct_date_from', CURRENT_DATE)\n" +
                "    AND ifnull('v_rct_date_to', CURRENT_DATE)\n" +
                "  AND stock_comp_code = v_stock_comp_code\n" +
                "  and stock_country_code = ifnull(v_stock_country_code, stock_country_code)\n" +
                "  and stock_region_code = ifnull(v_stock_region_code, stock_region_code)\n" +
                "  and stock_brn_code = ifnull(v_stock_brn_code, stock_brn_code)\n" +
                "  and cluster_CODE = ifnull(v_cluster_code, cluster_CODE)\n" +
                "order by rct_updated_on desc";
        try {
            sql = sql.replace("v_stock_comp_code", stockDetailsDto.getCompanyCode());
            sql = sql.replace("v_stock_country_code", stockDetailsDto.getStockCountryCode());
            sql = sql.replace("v_stock_region_code", stockDetailsDto.getStockRegionCode());
            sql = sql.replace("v_stock_brn_code", stockDetailsDto.getStockBranchCode());
            sql = sql.replace("v_cluster_code", stockDetailsDto.getStockClusterCode());
            sql = sql.replace("v_rct_date_from", stockDetailsDto.getStockDateFrom());
            sql = sql.replace("v_rct_date_to", stockDetailsDto.getStockDateTo());
            sql = sql.replace("'null'", "null");
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
                stockDetailsRes.setStockCreatedOn(rs.getString("stock_created_on"));
                stockDetailsRes.setStockUpdatedOn(rs.getString("rct_updated_on"));
                stockDetailsRes.setStockBatchNo(rs.getString("batch_no"));
                stockDetailsRes.setStockClusterCode(rs.getInt("cluster_code"));
                stockDetailsRes.setStockClusterName(rs.getString("cluster_name"));
                stockDetailsResList.add(stockDetailsRes);
            }
            System.out.println("stockDetailsResList " + stockDetailsResList.size());
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            Page<StockDetailsRes> stockDetailsResPage = CommonUtils.pageData(stockDetailsResList, pageable);
            symuResponse.setData(stockDetailsResPage);
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

    @Override
    public SymuResponse getAllStockDetailsByImei(String stockImei, int stockStatusCode, Pageable pageable) {
        SymuResponse symuResponse = new SymuResponse();
        Connection conn = null;
        CallableStatement cst = null;
        String sql = "SELECT stock_code,stock_imei,stock_selling_price,stock_defaulted,\n" +
                "                                ctry_currency_code,stock_brn_code,stock_region_code,stock_country_code,\n" +
                "                                       model_name,stock_created_on,rct_updated_on,\n" +
                "                                       customer_name,customer_phone,customer_national_id,\n" +
                "                                       agent_name,\n" +
                "                                       brn_name,\n" +
                "                                       dealer_name,batch_no,cluster_code,cluster_name\n" +
                "                                FROM stock,stock_model,customer,agents,branches,\n" +
                "                                dealership,countries,receipts,stock_batch,cluster\n" +
                "                                WHERE stock_model_code=model_code\n" +
                "                                  and rct_stock_code=stock_code\n" +
                "                                  and stock_customer_code=customer_code\n" +
                "                                  and stock_agn_code=agent_code\n" +
                "                                  and stock_brn_code=BRN_CODE\n" +
                "                                  and stock_dealer_code=dealer_code\n" +
                "                                  and stock_country_code=ctry_code\n" +
                "                                  and stock_batch_code=batch_code\n" +
                "                                  and stock_cluster_code=cluster_code\n" +
                "                                  and cluster_code=rct_cluster_code\n" +
                "                                  and stock_status_code=v_stock_status_code\n" +
                "                                  AND stock_imei like '%v_stock_imei'\n" +
                "                                 order by rct_updated_on desc";
        try {
            sql = sql.replace("v_stock_imei", stockImei);
            sql = sql.replace("v_stock_status_code", String.valueOf(stockStatusCode));
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
                stockDetailsRes.setStockCreatedOn(rs.getString("stock_created_on"));
                stockDetailsRes.setStockUpdatedOn(rs.getString("rct_updated_on"));
                stockDetailsRes.setStockBatchNo(rs.getString("batch_no"));
                stockDetailsRes.setStockClusterCode(rs.getInt("cluster_code"));
                stockDetailsRes.setStockClusterName(rs.getString("cluster_name"));
                stockDetailsResList.add(stockDetailsRes);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            Page<StockDetailsRes> stockDetailsResPage = CommonUtils.pageData(stockDetailsResList, pageable);
            symuResponse.setData(stockDetailsResPage);
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

    @Override
    public SymuResponse deleteStock(int stockCode, int userCode) {
        LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        SymuResponse symuResponse = new SymuResponse();
        try {
            StockStatusEntity stockStatusEntity = stockStatusRepository.getStockStatusEntitiesByStatusShortDesc(
                    "DELETED");
            StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockCode);
            stockEntityData.setStockStatusCode(stockStatusEntity.getStatusCode());
            stockEntityData.setStockImei(timestamp + "_" + stockEntityData.getStockImei());
            stockEntityData.setStockUpdatedOn(timestamp);
            stockEntityData.setStockUpdatedBy(userCode);
            StockEntity deleted = stockEntityRepo.save(stockEntityData);
            if (deleted != null) {
                //deleted
                symuResponse.setStatusCode("0");
                symuResponse.setMessage("Success");
                symuResponse.setData("Stock was deleted successfully");
            }
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
