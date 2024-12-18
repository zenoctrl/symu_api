package com.example.symu_api.CLUSTER.Service;

import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import com.example.symu_api.CLUSTER.Repository.ClusterEntityRepository;
import com.example.symu_api.COMMON.Model.SymuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterServiceImpl implements ClusterService {
    @Autowired
    private ClusterEntityRepository clusterEntityRepository;
    @Override
    public SymuResponse createOrUpdateCluster(ClusterEntity clusterEntity) {
        SymuResponse symuResponse = new SymuResponse();
        try{
            ClusterEntity cluster=clusterEntityRepository.save(clusterEntity);
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
                    getClusterEntitiesByClusterCountryCodeAndClusterStatus(
                    countryCode,"ACTIVE");
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
                    getClusterEntitiesByClusterBranchCodeAndClusterStatus(branchCode,"ACTIVE");
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
