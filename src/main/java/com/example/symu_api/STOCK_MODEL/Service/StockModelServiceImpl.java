package com.example.symu_api.STOCK_MODEL.Service;

import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Repository.StockModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockModelServiceImpl implements StockModelService{
    @Autowired
    private StockModelRepo stockModelRepo;

    @Override
    public StockModelEntity createOrUpdateStockModel(StockModelEntity stockModelEntity) {
        return stockModelRepo.save(stockModelEntity);
    }

    @Override
    public List<StockModelEntity>  getAllStockModels() {
        return stockModelRepo.findAll();
    }

    @Override
    public StockModelEntity getStockModelByCode(int stockModelCode) {
        return stockModelRepo.getStockModelEntitiesByCode(stockModelCode);
    }

    @Override
    public List<StockModelEntity> getStockModelEntitiesByModelStatus(String modelStatus) {
        return stockModelRepo.getStockModelEntitiesByModelStatus(modelStatus);
    }
}
