package com.example.symu_api.STOCK.Controller;

import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/createOrUpdateStock")
    public StockEntity createOrUpdateStock(@RequestBody StockEntity stockEntity) {
        return stockService.createOrUpdateStock(stockEntity);
    }
    @GetMapping(path = "/getStockEntityByStockCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<StockEntity> getStockEntityByStockCode(
            @RequestParam("stockCode")int stockCode
    ) {
        final StockEntity stockEntity =stockService.getStockEntityByStockCode(stockCode);
        return ResponseEntity.ok(stockEntity);
    }
    @GetMapping(path = "/getStockByBranchAndStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<StockEntity>> getStockByBranchAndStatus(
            @RequestParam("branchCode")int branchCode,
            @RequestParam("statusCode")int statusCode
    ) {
        final List<StockEntity> stockEntity =stockService.getStockByBranchAndStatus(branchCode, statusCode);
        return ResponseEntity.ok(stockEntity);
    }
}
