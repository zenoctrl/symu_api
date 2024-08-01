package com.example.symu_api.STOCK_BATCH.Repository;

import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockBatchRepo extends JpaRepository<StockBatchEntity,Long> {

    StockBatchEntity getStockBatchEntitiesByCode(int code);

    List<StockBatchEntity> getStockBatchEntitiesByBatchStatus(String status);
}
