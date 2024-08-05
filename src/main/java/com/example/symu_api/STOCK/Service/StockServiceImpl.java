package com.example.symu_api.STOCK.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
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
            stockEntity.setStockStatusCode(stock.getStockStatusCode());
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
}
