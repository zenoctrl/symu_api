package com.example.symu_api.STOCK.Service;

import com.example.symu_api.STOCK.Entity.StockEntity;

import java.util.List;

public interface StockService {
    StockEntity createOrUpdateStock(StockEntity stock);
    StockEntity getStockEntityByStockCode(int stockCode);
    List<StockEntity> getStockByBranchAndStatus(int brnCode,int stockStatusCode);
}
