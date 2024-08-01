package com.example.symu_api.BRANCHES.Service;


import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.COMMON.Model.SymuResponse;

import java.util.List;

public interface BranchService {


    SymuResponse createOrUpdateBranch(BranchEntity branchEntity);

    SymuResponse getAllBranches(int compCode);

    SymuResponse deleteABranch(int compCode, int brnCode) throws Exception;
}
