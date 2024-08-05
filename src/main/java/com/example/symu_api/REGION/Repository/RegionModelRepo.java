package com.example.symu_api.REGION.Repository;

import com.example.symu_api.REGION.Entity.RegionEntity;
import com.example.symu_api.REGION.Model.RegionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionModelRepo extends JpaRepository<RegionModel,Long> {
    RegionModel getRegionEntitiesByCode(int code);
    List<RegionModel> getRegionEntitiesByRegionCompanyCode(int regionCompCode);
    List<RegionModel> getRegionEntitiesByRegionCompanyCodeAndRegionCountryCode(int regionCompCode,int countryCode);
}
