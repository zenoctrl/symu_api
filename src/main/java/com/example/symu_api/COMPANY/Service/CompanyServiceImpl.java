package com.example.symu_api.COMPANY.Service;

import com.example.symu_api.COMPANY.Entity.CompanyEntity;
import com.example.symu_api.COMPANY.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public CompanyEntity createOrUpdateCompany(CompanyEntity companyDto) {
        CompanyEntity companyEntity=new CompanyEntity();
        try{
            CompanyEntity companyEntityData=companyRepository.getAllByCode(companyDto.getCode());
            companyEntity.setCode(companyEntityData.getCode());
        }catch (Exception e){
            // new company
        }
        companyEntity.setCompName(companyDto.getCompName());
        companyEntity.setCompEmail(companyDto.getCompEmail());
        companyEntity.setCompPhone(companyDto.getCompPhone());
        companyEntity.setCompShortDesc(companyDto.getCompShortDesc());
        companyEntity.setCompDesc(companyDto.getCompDesc());
        companyEntity.setCompStatus("A");
        CompanyEntity companyEntitySaved=companyRepository.save(companyEntity);
        return companyEntitySaved;
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyEntity getAllCompanyDetails(int compCode) {
        return companyRepository.getAllByCode(compCode);
    }
}
