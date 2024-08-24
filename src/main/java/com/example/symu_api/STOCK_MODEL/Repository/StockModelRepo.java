package com.example.symu_api.STOCK_MODEL.Repository;

import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockModelRepo extends JpaRepository<StockModelEntity,Long> {
    StockModelEntity getStockModelEntitiesByCode(int code);


}
