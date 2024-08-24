package com.example.symu_api.STOCK_MODEL.Repository;

import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Model.StockModelEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockModelEntityModelRepo extends JpaRepository<StockModelEntityModel,Long> {

    List<StockModelEntityModel> getStockModelEntitiesByModelStatus(String status);
}
