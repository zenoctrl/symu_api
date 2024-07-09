package com.example.symu_api.BRANCHES.Controller;



import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/branch")
public class BranchController {
    @Autowired
    BranchService branchService;

    @PostMapping("/createOrUpdateBranch")
    public BranchEntity createOrUpdateBranch(BranchEntity branchEntity) {
            return branchService.createOrUpdateBranch(branchEntity);
    }

    @GetMapping(path = "/getBranches",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<BranchEntity>> getBranches(
            @RequestParam("compCode")int compCode
    ) {
        final List<BranchEntity> branchEntities = branchService.getAllBranches(compCode);
        return ResponseEntity.ok(branchEntities);
    }

    @DeleteMapping(path = "/deleteBranch",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<BranchEntity> deleteBranche(
            @RequestParam("compCode")int compCode,
            @RequestParam("brachCode")int branchCode
    ) throws Exception {
        final BranchEntity branches = branchService.deleteABranch(compCode,branchCode);
        return ResponseEntity.ok(branches);
    }
}
