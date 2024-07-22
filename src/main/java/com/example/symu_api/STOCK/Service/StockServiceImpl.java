package com.example.symu_api.STOCK.Service;

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
    public StockEntity createOrUpdateStock(StockEntity stock) {
        LocalDateTime timestamp =LocalDateTime.now(ZoneId.of("Africa/Nairobi"));
        StockEntity stockEntity = new StockEntity();
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
        stockEntity.setStockCompCode(stock.getStockCompCode());
        stockEntity.setStockRegionCode(stock.getStockRegionCode());
        stockEntity.setStockBrnCode(stock.getStockBrnCode());
        stockEntity.setStockAgnCode(stock.getStockAgnCode());
        stockEntity.setStockImei(stock.getStockImei());
        stockEntity.setStockModelCode(stock.getStockModelCode());
        stockEntity.setStockMemory(stock.getStockMemory());
        stockEntity.setStockBuyingPrice(stock.getStockBuyingPrice());
        stockEntity.setStockSellingPrice(stock.getStockSellingPrice());
        stockEntity.setStockProfit(stock.getStockProfit());
        stockEntity.setStockStatusCode(stock.getStockStatusCode());
        stockEntity.setStockBaseCurrency(stock.getStockBaseCurrency());
        return  stockEntityRepo.save(stockEntity);
    }

    @Override
    public StockEntity getStockEntityByStockCode(int stockCode) {
        return stockEntityRepo.getStockEntitiesByCode(stockCode);
    }

    @Override
    public List<StockEntity> getStockByBranchAndStatus(int brnCode,int stockStatusCode) {
        return stockEntityRepo.getStockEntitiesByStockBrnCodeAndStockStatusCode(brnCode,stockStatusCode);
    }
}
