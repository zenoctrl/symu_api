package com.example.symu_api.COMPANY.Service;

import com.example.symu_api.COMPANY.Entity.CompanyEntity;

import java.util.List;

public interface CompanyService {
    CompanyEntity createOrUpdateCompany(CompanyEntity companyDto);

    List<CompanyEntity> getAllCompanies();

    CompanyEntity getAllCompanyDetails(int compCode);
}
