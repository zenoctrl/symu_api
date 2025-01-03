package com.example.symu_api.CLUSTER.Repository;

import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClusterEntityRepository extends JpaRepository<ClusterEntity, Long> {

    ClusterEntity getClusterEntitiesByCode(int code);

    List<ClusterEntity> getClusterEntitiesByClusterBranchCode(int branchCode);

    List<ClusterEntity> getClusterEntitiesByClusterCountryCode(int countryCode);

}
