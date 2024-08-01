package com.example.symu_api.STOCK.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK.Entity.StockEntity;

import java.util.List;

public interface StockService {
    SymuResponse createOrUpdateStock(StockEntity stock);
    SymuResponse getStockEntityByStockCode(int stockCode);
    SymuResponse getStockByBranchAndStatus(int brnCode,int stockStatusCode);
}
