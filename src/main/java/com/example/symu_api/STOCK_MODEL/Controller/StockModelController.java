package com.example.symu_api.STOCK_MODEL.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Service.StockModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/stock-model")
public class StockModelController {
    @Autowired
    private StockModelService stockModelService;

    @PostMapping("/createOrUpdateStockModel")
    public SymuResponse createOrUpdateStockModel(@RequestBody StockModelEntity stockModelEntity) {
        return stockModelService.createOrUpdateStockModel(stockModelEntity);
    }
    @GetMapping(path = "/getAllStockModels",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllStockModels(
    ) {
        final SymuResponse stockModelEntityList =stockModelService.getAllStockModels();
        return ResponseEntity.ok(stockModelEntityList);
    }
    @GetMapping(path = "/getStockModelByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockModelByCode(
            @RequestParam("stockModelCode")int stockModelCode
    ) {
        final SymuResponse stockModelEntity =stockModelService.getStockModelByCode(stockModelCode);
        return ResponseEntity.ok(stockModelEntity);
    }
    @GetMapping(path = "/getStockModelEntitiesByModelStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockModelEntitiesByModelStatus(
            @RequestParam("stockModelStatus")String stockModelStatus
    ) {
        final SymuResponse stockModelEntityList =stockModelService.getStockModelEntitiesByModelStatus(stockModelStatus);
        return ResponseEntity.ok(stockModelEntityList);
    }
}
