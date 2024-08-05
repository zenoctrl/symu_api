package com.example.symu_api.REGION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.REGION.Entity.RegionEntity;
import com.example.symu_api.REGION.Model.RegionModel;
import com.example.symu_api.REGION.Repository.RegionEntityRepo;
import com.example.symu_api.REGION.Repository.RegionModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionEntityRepo regionEntityRepo;
    @Autowired
    private RegionModelRepo regionModelRepo;
    @Override
    public SymuResponse createOrUpdateRegion(RegionEntity region) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            RegionEntity regionEntity=regionEntityRepo.save(region);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(regionEntity);
        }catch (Exception e){

            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getRegionEntitiesByCode(int code) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            RegionModel regionEntity=regionModelRepo.getRegionEntitiesByCode(code);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(regionEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getRegionEntitiesByRegionCompCode(int regionCompCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<RegionModel> regionEntityList=regionModelRepo.getRegionEntitiesByRegionCompanyCode(regionCompCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(regionEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getRegionByCountryCode(
            int regionCompCode, int countryCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<RegionModel> regionEntityList=regionModelRepo.getRegionEntitiesByRegionCompanyCodeAndRegionCountryCode(
                    regionCompCode, countryCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(regionEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}

