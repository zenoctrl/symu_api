package com.example.symu_api.STOCK_MODEL.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;

import java.util.List;

public interface StockModelService {
    SymuResponse createOrUpdateStockModel(StockModelEntity stockModelEntity);
    SymuResponse  getAllStockModels();
    SymuResponse getStockModelByCode(int stockModelCode);
    SymuResponse getStockModelEntitiesByModelStatus(String modelStatus);
}
