package com.example.symu_api.REGION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.REGION.Entity.RegionEntity;

import java.util.List;

public interface RegionService {
    SymuResponse createOrUpdateRegion(RegionEntity region);
    SymuResponse getRegionEntitiesByCode(int code);
    SymuResponse getRegionEntitiesByRegionCompCode(int regionCompCode);
    SymuResponse getRegionByCountryCode(int regionCompanyCode,int countryCode);
}
