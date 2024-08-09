package com.example.symu_api.STOCK_STATUS.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Service.StockBatchService;
import com.example.symu_api.STOCK_STATUS.Service.StockStatusService;
import com.example.symu_api.STOCK_STATUS.entity.StockStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/stock-status")
public class StockStatusController {
    @Autowired
    private StockStatusService stockStatusService;

 /*   @PostMapping("/creatOrUpdateStockStatus")
    public SymuResponse creatOrUpdateStockStatus(@RequestBody StockStatusEntity stockStatusEntity) {
        return stockStatusService.creatOrUpdateStockStatus(stockStatusEntity);
    }*/
    @GetMapping(path = "/getAllStockStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllStockStatus(
    ) {
        final SymuResponse stockStatusEntities =stockStatusService.getAllStockStatus();
        return ResponseEntity.ok(stockStatusEntities);
    }
    @GetMapping(path = "/getStockStatusByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockStatusByCode(
            @RequestParam("stockStatusCode")int stockStatusCode
    ) {
        final SymuResponse stockStatusEntities =stockStatusService.getStockStatusByCode(stockStatusCode);
        return ResponseEntity.ok(stockStatusEntities);
    }
    @GetMapping(path = "/getStockStatusByShortDesc",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockBatchEntitiesByBatchStatus(
            @RequestParam("stockStatusShortDesc")String stockStatusShortDesc
    ) {
        final SymuResponse stockStatusEntities =stockStatusService.getStockStatusByShortDesc(
                stockStatusShortDesc);
        return ResponseEntity.ok(stockStatusEntities);
    }
}
