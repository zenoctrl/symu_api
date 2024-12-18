package com.example.symu_api.CLUSTER.Service;

import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import com.example.symu_api.COMMON.Model.SymuResponse;

import java.util.List;

public interface ClusterService {
    SymuResponse createOrUpdateCluster(ClusterEntity clusterEntity);
    SymuResponse getClusterByCode(int code);
    SymuResponse getAllClusters(int countryCode);
    SymuResponse getAllClustersByBranch(int branchCode);
}
