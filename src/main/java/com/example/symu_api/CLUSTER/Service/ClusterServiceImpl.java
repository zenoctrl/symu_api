package com.example.symu_api.CLUSTER.Service;

import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Repository.BranchRepository;
import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import com.example.symu_api.CLUSTER.Repository.ClusterEntityRepository;
import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import com.example.symu_api.RECEIPT.Repository.ReceiptRepository;
import com.example.symu_api.STOCK.Entity.StockEntity;
import com.example.symu_api.STOCK.Repository.StockEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterServiceImpl implements ClusterService {
    @Autowired
    private ClusterEntityRepository clusterEntityRepository;
    @Autowired
    private StockEntityRepo stockEntityRepo;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Override
    public SymuResponse createOrUpdateCluster(ClusterEntity clusterEntity) {
        SymuResponse symuResponse = new SymuResponse();
        try{
            ClusterEntity cluster=clusterEntityRepository.save(clusterEntity);
            //migration
            BranchEntity branchEntity=branchRepository.findAllByCode(cluster.getClusterBranchCode());
            if(branchEntity.getName().equalsIgnoreCase(cluster.getClusterName())){
                List<StockEntity> stockEntityList=stockEntityRepo.getAllByStockBranchCode(cluster.getClusterBranchCode());
                for(StockEntity stockEntity:stockEntityList){
                    stockEntity.setStockClusterCode(cluster.getCode());
                    StockEntity updatedStock=stockEntityRepo.save(stockEntity);
                       // update receipts
                       List<ReceiptEntity> receiptEntityList=receiptRepository.getAllByReceiptStockCode(updatedStock.getCode());
                       for(ReceiptEntity receiptEntity:receiptEntityList){
                           receiptEntity.setReceiptClusterCode(cluster.getCode());
                           ReceiptEntity saved=receiptRepository.save(receiptEntity);
                           if (saved.getReceiptCode() !=null){
                               //updated
                           }
                       }
                }
            }
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(cluster);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Filed");
            symuResponse.setData(e.getLocalizedMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllClusters(int countryCode) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            List<ClusterEntity> clusterEntityList=clusterEntityRepository.
                    getClusterEntitiesByClusterCountryCode(countryCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(clusterEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Filed");
            symuResponse.setData(e.getLocalizedMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getClusterByCode(int code) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            ClusterEntity cluster=clusterEntityRepository.getClusterEntitiesByCode(code);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(cluster);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Filed");
            symuResponse.setData(e.getLocalizedMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllClustersByBranch(int branchCode) {
        SymuResponse symuResponse = new SymuResponse();
        try {
            List<ClusterEntity> clusterEntityList=clusterEntityRepository.
                    getClusterEntitiesByClusterBranchCode(branchCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(clusterEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Filed");
            symuResponse.setData(e.getLocalizedMessage());
        }
     return symuResponse;
    }
}
