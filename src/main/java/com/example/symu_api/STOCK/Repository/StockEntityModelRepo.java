package com.example.symu_api.STOCK.Repository;

import com.example.symu_api.STOCK.Model.StockEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockEntityModelRepo extends JpaRepository<StockEntityModel, Long> {
    StockEntityModel getStockEntitiesByCode(int stockCode);

    List<StockEntityModel> getStockEntitiesByStockBranchCodeAndStockStatusCode(int brnCode, int stockStatusCode);

    List<StockEntityModel> getStockEntitiesByStockCompanyCode(int companyCode);

}
