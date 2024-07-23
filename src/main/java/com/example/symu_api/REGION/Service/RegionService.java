package com.example.symu_api.REGION.Service;

import com.example.symu_api.REGION.Entity.RegionEntity;

import java.util.List;

public interface RegionService {
    RegionEntity createOrUpdateRegion(RegionEntity region);
    RegionEntity getRegionEntitiesByCode(int code);
    List<RegionEntity> getRegionEntitiesByRegionCompCode(int regionCompCode);
    List<RegionEntity> getRegionByCountryCode(int regionCompCode,int countryCode);
}
