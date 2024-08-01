package com.example.symu_api.COMPANY.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
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
    public SymuResponse createOrUpdateCompany(CompanyEntity companyDto) {
        SymuResponse symuResponse=new SymuResponse<>();
      try{
          CompanyEntity companyEntity=new CompanyEntity();
          try{
              CompanyEntity companyEntityData=companyRepository.getAllByCode(companyDto.getCode());
              companyEntity.setCode(companyEntityData.getCode());
          }catch (Exception e){
              // new company
          }
          companyEntity.setCompanyName(companyDto.getCompanyName());
          companyEntity.setCompanyEmail(companyDto.getCompanyEmail());
          companyEntity.setCompanyPhone(companyDto.getCompanyPhone());
          companyEntity.setCompanyShortDesc(companyDto.getCompanyShortDesc());
          companyEntity.setCompanyDesc(companyDto.getCompanyDesc());
          companyEntity.setCompanyStatus("A");
          CompanyEntity companyEntitySaved=companyRepository.save(companyEntity);
          symuResponse.setStatusCode("0");
          symuResponse.setMessage("Success");
          symuResponse.setData(companyEntitySaved);
      }catch (Exception e){
          symuResponse.setStatusCode("0");
          symuResponse.setMessage("failed");
          symuResponse.setData(e.getMessage());
      }
      return symuResponse;
    }

    @Override
    public SymuResponse getAllCompanies() {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<CompanyEntity> companyEntityList=companyRepository.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(companyEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllCompanyDetails(int compCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            CompanyEntity companyEntity=companyRepository.getAllByCode(compCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(companyEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("Failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
