package com.example.symu_api.STOCK_MODEL.Service;

import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;

import java.util.List;

public interface StockModelService {
    StockModelEntity createOrUpdateStockModel(StockModelEntity stockModelEntity);
    List<StockModelEntity>  getAllStockModels();
    StockModelEntity getStockModelByCode(int stockModelCode);
    List<StockModelEntity> getStockModelEntitiesByModelStatus(String modelStatus);
}
