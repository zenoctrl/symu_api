package com.example.symu_api.STOCK_MODEL.Controller;

import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Service.StockModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/symu/stock-model")
public class StockModelController {
    @Autowired
    private StockModelService stockModelService;

    @PostMapping("/createOrUpdateStockModel")
    public StockModelEntity createOrUpdateStockModel(@RequestBody StockModelEntity stockModelEntity) {
        return stockModelService.createOrUpdateStockModel(stockModelEntity);
    }
    @GetMapping(path = "/getAllStockModels",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<StockModelEntity>> getAllStockModels(
    ) {
        final List<StockModelEntity> stockModelEntityList =stockModelService.getAllStockModels();
        return ResponseEntity.ok(stockModelEntityList);
    }
    @GetMapping(path = "/getStockModelByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<StockModelEntity> getStockModelByCode(
            @RequestParam("stockModelCode")int stockModelCode
    ) {
        final StockModelEntity stockModelEntity =stockModelService.getStockModelByCode(stockModelCode);
        return ResponseEntity.ok(stockModelEntity);
    }
    @GetMapping(path = "/getStockModelEntitiesByModelStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<StockModelEntity>> getStockModelEntitiesByModelStatus(
            @RequestParam("stockModelStatus")String stockModelStatus
    ) {
        final List<StockModelEntity> stockModelEntityList =stockModelService.getStockModelEntitiesByModelStatus(stockModelStatus);
        return ResponseEntity.ok(stockModelEntityList);
    }
}
