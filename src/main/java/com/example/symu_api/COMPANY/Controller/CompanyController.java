package com.example.symu_api.COMPANY.Controller;

import com.example.symu_api.BRANCHES.Entity.BranchEntity;
import com.example.symu_api.COMPANY.Entity.CompanyEntity;
import com.example.symu_api.COMPANY.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/createOrUpdateCompany")
    public CompanyEntity createOrUpdateCompany(CompanyEntity companyDto) {
        return companyService.createOrUpdateCompany(companyDto);
    }
    @GetMapping(path = "/getAllCompanies",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<CompanyEntity>> getCompany(
    ) {
        final List<CompanyEntity> companyEntities =companyService.getAllCompanies();
        return ResponseEntity.ok(companyEntities);
    }
    @GetMapping(path = "/getCompanyDetails",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<CompanyEntity> getCompanyDetails(
            @RequestParam("compCode")int compCode
    ) {
        final CompanyEntity companyDetails = companyService.getAllCompanyDetails(compCode);
        return ResponseEntity.ok(companyDetails);
    }
}
