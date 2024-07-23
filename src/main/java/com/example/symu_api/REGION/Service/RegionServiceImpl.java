package com.example.symu_api.REGION.Service;

import com.example.symu_api.REGION.Entity.RegionEntity;
import com.example.symu_api.REGION.Repository.RegionEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionEntityRepo regionEntityRepo;
    @Override
    public RegionEntity createOrUpdateRegion(RegionEntity region) {
        return regionEntityRepo.save(region);
    }

    @Override
    public RegionEntity getRegionEntitiesByCode(int code) {
        return regionEntityRepo.getRegionEntitiesByCode(code);
    }

    @Override
    public List<RegionEntity> getRegionEntitiesByRegionCompCode(int regionCompCode) {
        return regionEntityRepo.getRegionEntitiesByRegionCompCode(regionCompCode);
    }

    @Override
    public List<RegionEntity> getRegionByCountryCode(
            int regionCompCode, int countryCode) {
        return regionEntityRepo.getRegionEntitiesByRegionCompCodeAndRegionCountryCode(
                regionCompCode, countryCode);
    }
}

