package com.example.symu_api.RECEIPT.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;
import com.example.symu_api.DEALERSHIP.Service.DealershipService;
import com.example.symu_api.RECEIPT.Service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/receipt")
public class ReceiptController {
@Autowired
private ReceiptService receiptService;


    @GetMapping(path = "/getAllByReceiptCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllByReceiptCode(
            @RequestParam("receiptCode")int receiptCode
    ) {
        final SymuResponse receipt=receiptService.getAllByReceiptCode(receiptCode);
        return ResponseEntity.ok(receipt);
    }
    @GetMapping(path = "/getAllByReceiptStockCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllByReceiptStockCode(
            @RequestParam("stockCode")int stockCode
    ) {
        final SymuResponse receiptList=receiptService.getAllByReceiptStockCode(stockCode);
        return ResponseEntity.ok(receiptList);
    }
}
