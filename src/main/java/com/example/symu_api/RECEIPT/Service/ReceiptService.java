package com.example.symu_api.RECEIPT.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;

import java.util.List;

public interface ReceiptService {

    SymuResponse getAllByReceiptCode(int receiptCode);

    SymuResponse getAllByReceiptStockCode(int stockCode);
}
