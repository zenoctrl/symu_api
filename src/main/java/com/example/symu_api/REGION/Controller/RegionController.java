package com.example.symu_api.REGION.Controller;

import com.example.symu_api.REGION.Entity.RegionEntity;
import com.example.symu_api.REGION.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/createOrUpdateRegion")
    public RegionEntity createOrUpdateRegion(@RequestBody RegionEntity regionEntity) {
        return regionService.createOrUpdateRegion(regionEntity);
    }
    @GetMapping(path = "/getRegionEntitiesByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<RegionEntity> getRegionEntitiesByCode(
            @RequestParam("regionCode")int regionCode
    ) {
        final RegionEntity regionEntity =regionService.getRegionEntitiesByCode(regionCode);
        return ResponseEntity.ok(regionEntity);
    }
    @GetMapping(path = "/getRegionByCompany",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<RegionEntity>> getRegionByCompany(
            @RequestParam("companyCode")int companyCode
    ) {
        final List<RegionEntity> regionEntityList =regionService.getRegionEntitiesByRegionCompCode(companyCode);
        return ResponseEntity.ok(regionEntityList);
    }
    @GetMapping(path = "/getRegionByCountryCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<RegionEntity>> getRegionByCountryCode(
            @RequestParam("companyCode")int companyCode,
            @RequestParam("countryCode")int countryCode
    ) {
        final List<RegionEntity> regionEntityList =regionService.getRegionByCountryCode(companyCode,countryCode);
        return ResponseEntity.ok(regionEntityList);
    }
}
