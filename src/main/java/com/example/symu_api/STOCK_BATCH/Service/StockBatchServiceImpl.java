package com.example.symu_api.STOCK_BATCH.Service;

import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockBatchServiceImpl implements StockBatchService {
    @Autowired
    private StockBatchRepo stockBatchRepo;

    @Override
    public StockBatchEntity createOrUpdateStockBatch(StockBatchEntity stockBatchEntity) {
        return stockBatchRepo.save(stockBatchEntity);
    }

    @Override
    public List<StockBatchEntity> getAllStockBatch() {
        return stockBatchRepo.findAll();
    }

    @Override
    public StockBatchEntity getStockBatchByCode(int stockModelCode) {
        return stockBatchRepo.getStockBatchEntitiesByCode(stockModelCode);
    }

    @Override
    public List<StockBatchEntity> getStockBatchEntitiesByBatchStatus(String stockStatus) {
        return stockBatchRepo.getStockBatchEntitiesByBatchStatus(stockStatus);
    }
}
