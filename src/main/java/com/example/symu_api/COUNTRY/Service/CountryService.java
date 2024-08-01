package com.example.symu_api.COUNTRY.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.COUNTRY.Entity.CountryEntity;

import java.util.List;

public interface CountryService {
    SymuResponse createOrUpdateCountry(CountryEntity country);
    SymuResponse getCountryByCode(int countryCode);
    SymuResponse getAllCountry();
}
