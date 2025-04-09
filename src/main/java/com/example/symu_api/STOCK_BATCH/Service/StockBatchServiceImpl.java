package com.example.symu_api.STOCK_BATCH.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COMMON.Service.DBUtils;
import com.example.symu_api.STOCK_BATCH.Entity.StockBatchEntity;
import com.example.symu_api.STOCK_BATCH.Model.BatchStatisticsModel;
import com.example.symu_api.STOCK_BATCH.Model.StockBatchModel;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchModelRepo;
import com.example.symu_api.STOCK_BATCH.Repository.StockBatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockBatchServiceImpl implements StockBatchService {
    @Autowired
    private StockBatchRepo stockBatchRepo;
    @Autowired
    private StockBatchModelRepo stockBatchModelRepo;
    @Autowired
    private DataSource dataSource;

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

    @Override
    public SymuResponse getStockBatchStatistics(int batchCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        Connection conn = null;
        CallableStatement cst = null;
        String sql ="SELECT count(stock_code) stockCount,status_name,status_short_desc\n" +
                "FROM stock,stock_status\n" +
                "where stock_status_code=status_code\n" +
                "and stock_batch_code=v_stock_batch_code\n" +
                "group by stock_status_code";
        try{
            sql = sql.replace("v_stock_batch_code", String.valueOf(batchCode));
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<BatchStatisticsModel> batchStatisticsModelList=new ArrayList<>();
            while (rs.next()) {
                BatchStatisticsModel batchStatisticsModel=new BatchStatisticsModel();
                batchStatisticsModel.setStockStatusName(rs.getString("status_name"));
                batchStatisticsModel.setStockStatusCount(rs.getInt("stockCount"));
                batchStatisticsModel.setStatusShortDesc(rs.getString("status_short_desc"));
                batchStatisticsModelList.add(batchStatisticsModel);
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(batchStatisticsModelList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Error occurred while fetching batch statistics");
            symuResponse.setData(e.getMessage());
        }finally {
            DBUtils.CloseConnections(null, cst, conn);
        }
        return symuResponse;
    }
}
