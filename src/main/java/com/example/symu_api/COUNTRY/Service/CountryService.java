package com.example.symu_api.COUNTRY.Service;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;

import java.util.List;

public interface CountryService {
    CountryEntity createOrUpdateCountry(CountryEntity country);
    CountryEntity getCountryByCode(int countryCode);
    List<CountryEntity> getAllCountry();
}
