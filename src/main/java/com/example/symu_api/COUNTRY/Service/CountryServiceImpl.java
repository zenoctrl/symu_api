package com.example.symu_api.COUNTRY.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import com.example.symu_api.COUNTRY.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
   @Autowired
    private CountryRepository countryRepository;

    @Override
    public SymuResponse createOrUpdateCountry(CountryEntity country) {
        SymuResponse symuResponse=new SymuResponse<>();
        CountryEntity countryEntity=new CountryEntity();
       try{
           try {
               CountryEntity countryEntityData=countryRepository.getCountryEntitiesByCode(countryEntity.getCode());
               countryEntity.setCode(countryEntityData.getCode());
               if(countryEntityData!=null) {
                   countryEntityData.setCode(countryEntity.getCode());
               }
           }catch(Exception e) {
               //  e.printStackTrace();
           }
           countryEntity.setCountryName(country.getCountryName());
           countryEntity.setCountryShortDesc(country.getCountryShortDesc());
           countryEntity.setCompanyCode(country.getCompanyCode());
           countryEntity.setCountryCountryCode(country.getCountryCountryCode());
           countryEntity.setCountryCurrencyCode(country.getCountryCurrencyCode());
           countryEntity.setStatus(country.getStatus());
           CountryEntity countryEntitySaved=countryRepository.save(countryEntity);

           symuResponse.setStatusCode("0");
           symuResponse.setMessage("Success");
           symuResponse.setData(countryEntitySaved);
       }catch (Exception e) {
           symuResponse.setStatusCode("1");
           symuResponse.setMessage("failed");
           symuResponse.setData(e.getMessage());
       }
       return symuResponse;
    }

    @Override
    public SymuResponse getCountryByCode(int countryCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            CountryEntity countryEntity=countryRepository.getCountryEntitiesByCode(countryCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(countryEntity);
        }catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllCountry() {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<CountryEntity> countryEntity=countryRepository.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(countryEntity);
        }catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
