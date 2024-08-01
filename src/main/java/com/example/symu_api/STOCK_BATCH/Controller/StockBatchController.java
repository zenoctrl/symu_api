package com.example.symu_api.STOCK_BATCH.Controller;

import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Service.StockBatchService;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Service.StockModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/stock-batch")
public class StockBatchController {
    @Autowired
    private StockBatchService stockBatchService;

    @PostMapping("/createOrUpdateStockBatch")
    public StockBatchEntity createOrUpdateStockModel(@RequestBody StockBatchEntity stockBatchEntity) {
        return stockBatchService.createOrUpdateStockBatch(stockBatchEntity);
    }
    @GetMapping(path = "/getAllStockBatch",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<StockBatchEntity>> getAllStockBatch(
    ) {
        final List<StockBatchEntity> stockBatchEntities =stockBatchService.getAllStockBatch();
        return ResponseEntity.ok(stockBatchEntities);
    }
    @GetMapping(path = "/getStockBatchByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<StockBatchEntity> getStockBatchByCode(
            @RequestParam("stockBatchCode")int stockBatchCode
    ) {
        final StockBatchEntity stockBatchEntity =stockBatchService.getStockBatchByCode(stockBatchCode);
        return ResponseEntity.ok(stockBatchEntity);
    }
    @GetMapping(path = "/getStockBatchEntitiesByBatchStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<StockBatchEntity>> getStockBatchEntitiesByBatchStatus(
            @RequestParam("stockBatchStatus")String stockBatchStatus
    ) {
        final List<StockBatchEntity> stockBatchEntities =stockBatchService.getStockBatchEntitiesByBatchStatus(
                stockBatchStatus);
        return ResponseEntity.ok(stockBatchEntities);
    }
}
