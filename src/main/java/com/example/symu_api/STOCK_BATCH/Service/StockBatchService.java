package com.example.symu_api.STOCK_BATCH.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;

import java.util.List;

public interface StockBatchService {
    SymuResponse createOrUpdateStockBatch(StockBatchEntity stockModelEntity);

    SymuResponse  getAllStockBatch();

    SymuResponse getStockBatchByCode(int code);

    SymuResponse getStockBatchEntitiesByBatchStatus(String stockStatus);

    SymuResponse getStockBatchStatistics(int batchCode);
}
