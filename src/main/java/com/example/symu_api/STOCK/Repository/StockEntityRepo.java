package com.example.symu_api.STOCK.Repository;

import com.example.symu_api.STOCK.Entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockEntityRepo extends JpaRepository<StockEntity, Long> {

    StockEntity getStockEntitiesByCode(int stockCode);

    List<StockEntity> getStockEntitiesByStockBranchCodeAndStockStatusCode(int brnCode,int stockStatusCode);

    List<StockEntity> getStockEntitiesByStockCompanyCode(int companyCode);
}
