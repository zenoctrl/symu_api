package com.example.symu_api.STOCK_BATCH.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Model.StockBatchModel;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchModelRepo;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockBatchServiceImpl implements StockBatchService {
    @Autowired
    private StockBatchRepo stockBatchRepo;
    @Autowired
    private StockBatchModelRepo stockBatchModelRepo;

    @Override
    public SymuResponse createOrUpdateStockBatch(StockBatchEntity stockBatchEntity) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            StockBatchEntity stockBatchEntity1=stockBatchRepo.save(stockBatchEntity);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockBatchEntity1);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllStockBatch() {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<StockBatchModel> stockBatchEntityList=stockBatchModelRepo.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockBatchEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockBatchByCode(int stockModelCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            StockBatchEntity stockBatchEntity=stockBatchRepo.getStockBatchEntitiesByCode(stockModelCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockBatchEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockBatchEntitiesByBatchStatus(String stockStatus) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            List<StockBatchEntity> stockBatchEntityList=stockBatchRepo.getStockBatchEntitiesByBatchStatus(stockStatus);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockBatchEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
