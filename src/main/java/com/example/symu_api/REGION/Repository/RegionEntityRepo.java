package com.example.symu_api.REGION.Repository;

import com.example.symu_api.REGION.Entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionEntityRepo extends JpaRepository<RegionEntity,Long> {
    RegionEntity getRegionEntitiesByCode(int code);
    List<RegionEntity> getRegionEntitiesByRegionCompanyCode(int regionCompanyCode);
    List<RegionEntity> getRegionEntitiesByRegionCompanyCodeAndRegionCountryCode(int regionCompanyCode,int countryCode);
}
