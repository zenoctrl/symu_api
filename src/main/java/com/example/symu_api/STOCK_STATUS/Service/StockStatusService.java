package com.example.symu_api.STOCK_STATUS.Service;


import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_STATUS.entity.StockStatusEntity;

public interface StockStatusService {
    SymuResponse creatOrUpdateStockStatus(StockStatusEntity stockStatusEntity);

    SymuResponse getStockStatusByCode(int statusCode);

    SymuResponse getStockStatusByShortDesc(String statusShortDesc);

    SymuResponse getAllStockStatus();
}
