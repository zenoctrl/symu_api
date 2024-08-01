package com.example.symu_api.BRANCHES.Service;


import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Repository.BranchRepository;
import com.example.symu_api.COMMON.Model.SymuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Override
    public SymuResponse createOrUpdateBranch(BranchEntity branchEntityDAO) {
        SymuResponse symuResponse = new SymuResponse<>();
        try {
            BranchEntity branchEntity = new BranchEntity();
            try {
                BranchEntity branchEntityData = branchRepository.findAllByCode(branchEntityDAO.getCode());
                //update branch details
                branchEntity.setCode(branchEntityData.getCode());
            } catch (Exception e) {
                //create new branch
                // branch.setCode(branchCodeGenerator());
            }
            branchEntity.setCompanyCode(branchEntityDAO.getCompanyCode());
            branchEntity.setShortDesc(branchEntityDAO.getShortDesc());
            branchEntity.setName(branchEntityDAO.getName());
            branchEntity.setDesc(branchEntityDAO.getDesc());
            branchEntity.setStatus("A");
            BranchEntity saved = branchRepository.save(branchEntity);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(saved);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllBranches(int compCode) {
        SymuResponse symuResponse = new SymuResponse<>();
        try {
            List<BranchEntity> branchEntityList = branchRepository.findAllByCompanyCodeAndStatus(compCode, "A");
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(branchEntityList);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse deleteABranch(int compCode, int brnCode) {
        SymuResponse symuResponse = new SymuResponse<>();
   try{
       BranchEntity branchEntity = branchRepository.findAllByCode(brnCode);
       if (branchEntity.getShortDesc().equalsIgnoreCase("HQT")) {
           symuResponse.setStatusCode("1");
           symuResponse.setMessage("Failed");
           symuResponse.setData("You cannot delete main branch");
       }else {
           branchEntity.setStatus("I");
           branchRepository.save(branchEntity);
           symuResponse.setStatusCode("0");
           symuResponse.setMessage("success");
           symuResponse.setData("Branch deleted successfully");
       }

   }catch (Exception e){
       symuResponse.setStatusCode("1");
       symuResponse.setMessage("Error occur while deleting branch");
       symuResponse.setData(e.getMessage());
   }
   return symuResponse;
    }
}
