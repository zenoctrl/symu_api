package com.example.symu_api.BRANCHES.Service;


import com.example.symu_api.BRANCHES.Entity.BranchEntity;

import java.util.List;

public interface BranchService {

int branchCodeGenerator();

BranchEntity createOrUpdateBranch(BranchEntity branchEntity);

    List<BranchEntity> getAllBranches(int compCode);

    BranchEntity deleteABranch(int compCode, int brnCode) throws Exception;
}
