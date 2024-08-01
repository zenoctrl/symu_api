package com.example.symu_api.COMPANY.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COMPANY.Entity.CompanyEntity;

import java.util.List;

public interface CompanyService {
    SymuResponse createOrUpdateCompany(CompanyEntity companyDto);

    SymuResponse getAllCompanies();

    SymuResponse getAllCompanyDetails(int compCode);
}
