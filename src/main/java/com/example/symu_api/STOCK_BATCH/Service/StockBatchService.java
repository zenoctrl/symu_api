package com.example.symu_api.STOCK_BATCH.Service;

import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;

import java.util.List;

public interface StockBatchService {
    StockBatchEntity createOrUpdateStockBatch(StockBatchEntity stockModelEntity);

    List<StockBatchEntity>  getAllStockBatch();

    StockBatchEntity getStockBatchByCode(int code);

    List<StockBatchEntity> getStockBatchEntitiesByBatchStatus(String stockStatus);
}
