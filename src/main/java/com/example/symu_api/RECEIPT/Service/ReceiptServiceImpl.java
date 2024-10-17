package com.example.symu_api.RECEIPT.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import com.example.symu_api.RECEIPT.Repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public SymuResponse getAllByReceiptCode(int receiptCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            ReceiptEntity receiptEntityList=receiptRepository.getAllByReceiptCode(receiptCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(receiptEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllByReceiptStockCode(int stockCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            List<ReceiptEntity> receiptEntityList=receiptRepository.getAllByReceiptStockCodeAndReceiptStatus(stockCode,"POSTED");
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(receiptEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
