package com.example.symu_api.STOCK_MODEL.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Repository.StockEntityRepo;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import com.example.symu_api.STOCK_MODEL.Repository.StockModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockModelServiceImpl implements StockModelService{
    @Autowired
    private StockModelRepo stockModelRepo;
    @Autowired
    private StockEntityRepo stockEntityRepo;

    @Override
    public SymuResponse createOrUpdateStockModel(StockModelEntity stockModelEntity) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            StockModelEntity stockModelSaved=stockModelRepo.save(stockModelEntity);
            List<StockEntity> stockEntityList=stockEntityRepo.getAllByStockCompanyCodeAndStockModelCodeAndStockStatusCode(
                    stockModelSaved.getModelCompanyCode(),stockModelSaved.getCode(),2
            );
            for(StockEntity i:stockEntityList){
                StockEntity stockEntityData = stockEntityRepo.getStockEntitiesByCode(i.getCode());
                stockEntityData.setStockSellingPrice(stockModelEntity.getModelSellingPrice());
                stockEntityData.setStockProfit(stockModelEntity.getModelSellingPrice() - stockEntityData.getStockBuyingPrice());
                StockEntity saved = stockEntityRepo.save(stockEntityData);
                if(saved!=null){
                    //saved
                }
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockModelSaved);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse  getAllStockModels() {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<StockModelEntity> stockModelEntity1=stockModelRepo.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockModelEntity1);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockModelByCode(int stockModelCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            StockModelEntity stockModelEntity1=stockModelRepo.getStockModelEntitiesByCode(stockModelCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockModelEntity1);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getStockModelEntitiesByModelStatus(String modelStatus) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<StockModelEntity> stockModelEntity1=stockModelRepo.getStockModelEntitiesByModelStatus(modelStatus);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(stockModelEntity1);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
