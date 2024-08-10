package com.example.symu_api.DEALERSHIP.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;
import com.example.symu_api.DEALERSHIP.Service.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/dealership")
public class DealershipController {
@Autowired
private DealershipService dealershipService;

    @PostMapping("/createOrUpdateDealership")
    public SymuResponse createOrUpdateDealership(@RequestBody DealershipEntity dealershipEntity) {
        return dealershipService.createOrUpdateDealership(dealershipEntity);
    }

    @GetMapping(path = "/getDealershipByDealerCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getDealershipByDealerCode(
            @RequestParam("dealerCode")int dealerCode
    ) {
        final SymuResponse dealership=dealershipService.getDealershipEntitiesByDealerCode(dealerCode);
        return ResponseEntity.ok(dealership);
    }
    @GetMapping(path = "/getDealershipByCountryCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getDealershipByCountryCode(
            @RequestParam("countryCode")int countryCode,
            @RequestParam("companyCode")int companyCode
    ) {
        final SymuResponse dealership=dealershipService.getDealershipEntitiesByCountryCode(countryCode,companyCode);
        return ResponseEntity.ok(dealership);
    }
}
