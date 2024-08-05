package com.example.symu_api.STOCK_STATUS.repository;


import com.example.symu_api.STOCK_STATUS.entity.StockStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StockStatusRepository extends JpaRepository<StockStatusEntity,Integer> {
    StockStatusEntity getStockStatusEntitiesByStatusCode(int statusCode);
    StockStatusEntity getStockStatusEntitiesByStatusShortDesc(String statusShortDesc);
}
