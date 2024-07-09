package com.example.symu_api.BRANCHES.Service;


import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class BranchServiceImpl implements BranchService{
    @Autowired
    BranchRepository branchRepository;

/*    @Autowired
    ExceptionService exceptionService;*/

    public int branchCodeGenerator(){
        try{
           return branchRepository.findMaxCode()+1;
        }catch (Exception e){
            return 202001;
        }

    }
    @Override
    public BranchEntity createOrUpdateBranch(BranchEntity branchEntityDAO) {
        BranchEntity branchEntity =new BranchEntity();
        try {
            BranchEntity branchEntityData =branchRepository.findAllByCode(branchEntityDAO.getCode());
            //update branch details
            branchEntity.setCode(branchEntityData.getCode());
        }catch (Exception e){
            //create new branch
           // branch.setCode(branchCodeGenerator());
        }
      branchEntity.setCompCode(branchEntityDAO.getCompCode());
      branchEntity.setShortDesc(branchEntityDAO.getShortDesc());
      branchEntity.setName(branchEntityDAO.getName());
      branchEntity.setDesc(branchEntityDAO.getDesc());
      branchEntity.setStatus("A");
        return branchRepository.save(branchEntity);
    }

    @Override
    public List<BranchEntity> getAllBranches(int compCode) {
        return branchRepository.findAllByCompCodeAndStatus(compCode,"A");
    }

    @Override
    public BranchEntity deleteABranch(int compCode, int brnCode) {
        BranchEntity branchEntity =branchRepository.findAllByCode(brnCode);
       if (branchEntity.getShortDesc().equalsIgnoreCase("HQT")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "You cannot delete main branch"
            );
        }
            branchEntity.setStatus("I");
            return branchRepository.save(branchEntity);
        }
}
