package com.example.symu_api.BRANCHES.Repository;



import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Model.BranchModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchModelRepository extends CrudRepository<BranchModel,Integer> {

    List<BranchModel> findAllByCompanyCodeAndStatus(int compCode, String status);

    BranchModel findAllByCompanyCodeAndCode(int compCode, int brchCode);

    BranchModel findAllByCode(int brchCode);
}
