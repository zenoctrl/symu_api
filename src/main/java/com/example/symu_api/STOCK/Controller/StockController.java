package com.example.symu_api.STOCK.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK.Dto.StockCloseSaleDto;
import com.example.symu_api.STOCK.Dto.StockPostSaleDto;
import com.example.symu_api.STOCK.Dto.StockPriceDto;
import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/createOrUpdateStock")
    public SymuResponse createOrUpdateStock(@RequestBody StockEntity stockEntity) {
        return stockService.createOrUpdateStock(stockEntity);
    }
    @GetMapping(path = "/getStockEntityByStockCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockEntityByStockCode(
            @RequestParam("stockCode")int stockCode
    ) {
        final SymuResponse stockEntity =stockService.getStockEntityByStockCode(stockCode);
        return ResponseEntity.ok(stockEntity);
    }
    @GetMapping(path = "/getStockByBranchAndStatus",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getStockByBranchAndStatus(
            @RequestParam("branchCode")int branchCode,
            @RequestParam("statusCode")int statusCode
    ) {
        final SymuResponse stockEntity =stockService.getStockByBranchAndStatus(branchCode, statusCode);
        return ResponseEntity.ok(stockEntity);
    }
    @GetMapping(path = "/getAllStock",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllStock(
            @RequestParam("companyCode")int companyCode,
            @RequestParam("statusShortDesc")String statusShortDesc
    ) {
        final SymuResponse stockEntity =stockService.getStockEntitiesByStockCompanyCode(companyCode,statusShortDesc);
        return ResponseEntity.ok(stockEntity);
    }
    @PostMapping("/updateStockPrice")
    public SymuResponse updateStockBuyingPrice(@RequestBody StockPriceDto stockPriceDto) {
        return stockService.updateStockPrice(stockPriceDto);
    }
    @PostMapping("/stockPostSale")
    public SymuResponse stockPostSale(@RequestBody StockPostSaleDto stockPostSaleDto) {
        return stockService.stockPostSale(stockPostSaleDto);
    }
    @PostMapping("/stockRejectPostedSale")
    public SymuResponse stockRejectPostedSale(
            @RequestParam("stockCode")int stockCode,
            @RequestParam("userCode")int userCode
    ) {
        return stockService.stockRejectPostedSale(stockCode,userCode);
    }
    @PostMapping("/stockCloseSale")
    public SymuResponse stockCloseSale(@RequestBody StockCloseSaleDto stockPostSaleDto) {
        return stockService.stockCloseSale(stockPostSaleDto);
    }
    @GetMapping(path = "/getAllStockDetails",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllStockDetails(
            @RequestParam("companyCode")int companyCode
    ) {
        final SymuResponse stockEntity =stockService.getAllStockDetails(companyCode);
        return ResponseEntity.ok(stockEntity);
    }
    @PostMapping("/updateDefaultStatus")
    public SymuResponse updateDefaultStatus(
            @RequestParam("stockCode")int stockCode,
            @RequestParam("defaultStatus")String defaultStatus) {
        return stockService.updateDefaultStatus(stockCode,defaultStatus);
    }
}
