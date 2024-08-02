package com.example.symu_api.STOCK_BATCH.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Service.StockBatchService;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Service.StockModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/stock-batch")
public class StockBatchController {
    @Autowired
    private StockBatchService stockBatchService;

    @PostMapping("/createOrUpdateStockBatch")
    public SymuResponse createOrUpdateStockModel(@RequestBody StockBatchEntity stockBatchEntity) {
        return stockBatchService.createOrUpdateStockBatch(stockBatchEntity);
    }
    @GetMapping(path = "/getAllStockBatch",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllStockBatch(
    ) {
        final SymuResponse stockBatchEntities =stockBatchService.getAllStockBatch();
        return ResponseEntity.ok(stockBatchEntities);
    }
    @GetMapping(path = "/getStockBatchByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockBatchByCode(
            @RequestParam("stockBatchCode")int stockBatchCode
    ) {
        final SymuResponse stockBatchEntity =stockBatchService.getStockBatchByCode(stockBatchCode);
        return ResponseEntity.ok(stockBatchEntity);
    }
    @GetMapping(path = "/getStockBatchEntitiesByBatchStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockBatchEntitiesByBatchStatus(
            @RequestParam("stockBatchStatus")String stockBatchStatus
    ) {
        final SymuResponse stockBatchEntities =stockBatchService.getStockBatchEntitiesByBatchStatus(
                stockBatchStatus);
        return ResponseEntity.ok(stockBatchEntities);
    }
}
