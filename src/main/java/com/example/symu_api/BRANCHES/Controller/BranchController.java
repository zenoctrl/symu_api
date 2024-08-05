package com.example.symu_api.BRANCHES.Controller;



import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.BRANCHES.Service.BranchService;
import com.example.symu_api.COMMON.Model.SymuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/branch")
public class BranchController {
    @Autowired
    BranchService branchService;

    @PostMapping("/createOrUpdateBranch")
    public SymuResponse createOrUpdateBranch(@RequestBody BranchEntity branchEntity) {
            return branchService.createOrUpdateBranch(branchEntity);
    }

    @GetMapping(path = "/getBranches",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getBranches(
            @RequestParam("companyCode")int companyCode
    ) {
        final SymuResponse branchEntities = branchService.getAllBranches(companyCode);
        return ResponseEntity.ok(branchEntities);
    }

    @DeleteMapping(path = "/deleteBranch",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> deleteBranche(
            @RequestParam("companyCode")int companyCode,
            @RequestParam("brachCode")int branchCode
    ) throws Exception {
        final SymuResponse branches = branchService.deleteABranch(companyCode,branchCode);
        return ResponseEntity.ok(branches);
    }
}
