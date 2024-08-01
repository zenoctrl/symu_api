package com.example.symu_api.BRANCHES.Repository;



import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<BranchEntity,Integer> {
    @Query(value = "SELECT  MAX (code) FROM BranchEntity")
    Integer findMaxCode();

    List<BranchEntity> findAllByCompanyCodeAndStatus(int compCode, String status);

    BranchEntity findAllByCompanyCodeAndCode(int compCode, int brchCode);

    BranchEntity findAllByCode(int brchCode);
}
