package com.example.symu_api.STOCK_STATUS.Service;


import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK_STATUS.entity.StockStatusEntity;
import com.example.symu_api.STOCK_STATUS.repository.StockStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockStatusServiceImpl implements StockStatusService {
    @Autowired
    StockStatusRepository stockStatusRepository;

    @Override
    public SymuResponse creatOrUpdateStockStatus(StockStatusEntity stockStatus) {
        SymuResponse symuResponse = new SymuResponse();
        StockStatusEntity stockStatusEntity=new StockStatusEntity();
      try{
          try{
              StockStatusEntity stockStatusEntityData=stockStatusRepository.getStockStatusEntitiesByStatusCode(
                      stockStatus.getStatusCode());
              stockStatusEntity.setStatusCode(stockStatusEntityData.getStatusCode());
          }catch (Exception e){
              //new status
          }
          stockStatusEntity.setStatusName(stockStatus.getStatusName());
          stockStatusEntity.setStatusShortDesc(stockStatus.getStatusShortDesc());
          stockStatusEntity.setStatusDescription(stockStatus.getStatusDescription());
          StockStatusEntity stockStatusEntitySaved=stockStatusRepository.save(stockStatusEntity);
          symuResponse.setStatusCode("0");
          symuResponse.setMessage("Success");
          symuResponse.setData(stockStatusEntitySaved);
      }catch (Exception e){
          symuResponse.setStatusCode("1");
          symuResponse.setMessage("failed");
          symuResponse.setData(e.getMessage());
      }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockStatusByCode(int statusCode) {
        SymuResponse symuResponse = new SymuResponse();
        try{
            StockStatusEntity stockStatusEntity=stockStatusRepository.getStockStatusEntitiesByStatusCode(statusCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockStatusEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockStatusByShortDesc(String statusShortDesc) {
        SymuResponse symuResponse = new SymuResponse();
        try{
            StockStatusEntity stockStatusEntity=stockStatusRepository.getStockStatusEntitiesByStatusShortDesc(statusShortDesc);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockStatusEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllStockStatus() {
        SymuResponse symuResponse = new SymuResponse();
        try{
            List<StockStatusEntity> stockStatusEntityList=stockStatusRepository.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockStatusEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
