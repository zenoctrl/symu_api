package com.example.symu_api.REGION.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.REGION.Entity.RegionEntity;
import com.example.symu_api.REGION.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/createOrUpdateRegion")
    public SymuResponse createOrUpdateRegion(@RequestBody RegionEntity regionEntity) {
        return regionService.createOrUpdateRegion(regionEntity);
    }
    @GetMapping(path = "/getRegionEntitiesByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getRegionEntitiesByCode(
            @RequestParam("regionCode")int regionCode
    ) {
        final SymuResponse regionEntity =regionService.getRegionEntitiesByCode(regionCode);
        return ResponseEntity.ok(regionEntity);
    }
    @GetMapping(path = "/getRegionByCompany",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getRegionByCompany(
            @RequestParam("companyCode")int companyCode
    ) {
        final SymuResponse regionEntityList =regionService.getRegionEntitiesByRegionCompCode(companyCode);
        return ResponseEntity.ok(regionEntityList);
    }
    @GetMapping(path = "/getRegionByCountryCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getRegionByCountryCode(
            @RequestParam("companyCode")int companyCode,
            @RequestParam("countryCode")int countryCode
    ) {
        final SymuResponse regionEntityList =regionService.getRegionByCountryCode(companyCode,countryCode);
        return ResponseEntity.ok(regionEntityList);
    }
}
