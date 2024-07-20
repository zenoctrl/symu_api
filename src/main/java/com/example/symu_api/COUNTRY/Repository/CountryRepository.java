package com.example.symu_api.COUNTRY.Repository;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity,Integer> {
    CountryEntity getCountryEntitiesByCode(int code);
}
