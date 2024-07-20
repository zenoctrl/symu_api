package com.example.symu_api.COUNTRY.Service;

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
    public CountryEntity createOrUpdateCountry(CountryEntity country) {
        CountryEntity countryEntity=new CountryEntity();
        try {
            CountryEntity countryEntityData=countryRepository.getCountryEntitiesByCode(countryEntity.getCode());
            if(countryEntityData!=null) {
                countryEntityData.setCode(countryEntity.getCode());
            }
        }catch(Exception e) {
          //  e.printStackTrace();
        }
        countryEntity.setCountryName(country.getCountryName());
        countryEntity.setCountryShortDesc(country.getCountryShortDesc());
        countryEntity.setStatus("A");
        CountryEntity countryEntitySaved=countryRepository.save(countryEntity);
        return countryEntitySaved;
    }

    @Override
    public CountryEntity getCountryByCode(int countryCode) {
        return countryRepository.getCountryEntitiesByCode(countryCode);
    }

    @Override
    public List<CountryEntity> getAllCountry() {
        return countryRepository.findAll();
    }
}
