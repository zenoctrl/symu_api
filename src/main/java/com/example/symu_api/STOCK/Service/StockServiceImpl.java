package com.example.symu_api.STOCK.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.CUSTOMER.Entity.CustomerEntity;
import com.example.symu_api.CUSTOMER.Repository.CustomerRepository;
import com.example.symu_api.STOCK.Dto.StockPostSaleDto;
import com.example.symu_api.STOCK.Dto.StockPriceDto;
import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Repository.StockEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockEntityRepo stockEntityRepo;
    @Autowired
    private CustomerRepository customerRepository;

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
            } catch (Exception e) {
                // new stock
                stockEntity.setStockCreatedOn(timestamp);
                stockEntity.setStockCreatedBy(stock.getStockCreatedBy());
                stockEntity.setStockStatusCode(stock.getStockStatusCode());
            }
            stockEntity.setStockCompanyCode(stock.getStockCompanyCode());
            stockEntity.setStockCountryCode(stock.getStockCountryCode());
            stockEntity.setStockRegionCode(stock.getStockRegionCode());
            stockEntity.setStockBranchCode(stock.getStockBranchCode());
            stockEntity.setStockBatchCode(stock.getStockBatchCode());
            stockEntity.setStockAgnCode(stock.getStockAgnCode());
            stockEntity.setStockImei(stock.getStockImei());
            stockEntity.setStockModelCode(stock.getStockModelCode());
            stockEntity.setStockMemory(stock.getStockMemory());
            stockEntity.setStockBuyingPrice(stock.getStockBuyingPrice());
            stockEntity.setStockSellingPrice(stock.getStockSellingPrice());
            stockEntity.setStockProfit(stock.getStockProfit());
            stockEntity.setStockDefaulted("N");
            stockEntity.setStockBaseCurrency(stock.getStockBaseCurrency());
            stockEntity.setStockSoldBy(stock.getStockSoldBy());
            stockEntity.setStockTradeName(stock.getStockTradeName());
            StockEntity stockEntity1 = stockEntityRepo.save(stockEntity);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockEntity1);
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
            StockEntity stockEntity = stockEntityRepo.getStockEntitiesByCode(stockCode);
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
            List<StockEntity> stockEntityList = stockEntityRepo.getStockEntitiesByStockBranchCodeAndStockStatusCode(brnCode, stockStatusCode);
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
        SymuResponse symuResponse=new SymuResponse<>();
      try{
          CustomerEntity customerEntity = new CustomerEntity();
          StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(stockPostSaleDto.getStockCode());
          try {
              CustomerEntity customerEntityData = customerRepository.getCustomerEntitiesByCustomerNationalId(
                      stockPostSaleDto.getCustomerNationalId());
              customerEntity.setCustomerCode(customerEntityData.getCustomerCode());
          } catch (Exception e) {
              // new cutomer
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
          stockEntityData.setStockCustomerCode(customerCode);
          StockEntity saved = stockEntityRepo.save(stockEntityData);

          symuResponse.setStatusCode("0");
          symuResponse.setMessage("success");
          symuResponse.setData(saved);
      }catch (Exception e){
          symuResponse.setStatusCode("1");
          symuResponse.setMessage("Failed");
          symuResponse.setData(e.getMessage());
      }
        return symuResponse;
    }
}
