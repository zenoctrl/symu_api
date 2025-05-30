package com.example.symu_api.STOCK.Service;

import com.example.symu_api.COMMON.Model.SymuBulkResponse;
import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK.Dto.*;
import com.example.symu_api.STOCK.Entity.StockEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {
    SymuResponse createOrUpdateStock(StockEntity stock);

    SymuBulkResponse createStockBulk(CreateStockBulkDto createStockBulkDto);

    SymuResponse getStockEntityByStockCode(int stockCode);

    SymuResponse getStockByBranchAndStatus(int brnCode,int stockStatusCode);

    SymuResponse getStockEntitiesByStockCompanyCode(GetAllStockDto getAllStockDto,
                                                    Pageable pageable);

    SymuResponse updateStockPrice(StockPriceDto stockPriceDto);

    SymuResponse stockPostSale(StockPostSaleDto stockPostSaleDto);

    SymuResponse stockApproval(StockApprovalDto stockApprovalDto);

    SymuResponse stockRejectPostedSale(int stockCode,int stockUserCode);

    SymuResponse stockCloseSale(StockCloseSaleDto stockCloseSaleDto);

    SymuResponse getAllStockDetails(StockDetailsDto stockDetailsDto,Pageable pageable);

    SymuResponse getAllStockDetailsByImei(String stockImei,int stockStatusCode,Pageable pageable);

    SymuResponse updateDefaultStatus(int stockCode,String defaultStatus);

    SymuResponse deleteStock(int stockCode,int userCode);
}
