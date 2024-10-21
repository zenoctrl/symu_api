package com.example.symu_api.STOCK.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK.Dto.StockCloseSaleDto;
import com.example.symu_api.STOCK.Dto.StockPostSaleDto;
import com.example.symu_api.STOCK.Dto.StockPriceDto;
import com.example.symu_api.STOCK.Entity.StockEntity;

import java.util.List;

public interface StockService {
    SymuResponse createOrUpdateStock(StockEntity stock);

    SymuResponse getStockEntityByStockCode(int stockCode);

    SymuResponse getStockByBranchAndStatus(int brnCode,int stockStatusCode);

    SymuResponse getStockEntitiesByStockCompanyCode(int companyCode,String statusShortDesc);

    SymuResponse updateStockPrice(StockPriceDto stockPriceDto);

    SymuResponse stockPostSale(StockPostSaleDto stockPostSaleDto);

    SymuResponse stockRejectPostedSale(int stockCode,int stockUserCode);

    SymuResponse stockCloseSale(StockCloseSaleDto stockCloseSaleDto);

    SymuResponse getAllStockDetails(int companyCode);

    SymuResponse updateDefaultStatus(int stockCode,String defaultStatus);
}
