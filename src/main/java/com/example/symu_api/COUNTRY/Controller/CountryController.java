package com.example.symu_api.COUNTRY.Controller;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import com.example.symu_api.COUNTRY.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/createOrUpdateCountry")
    public CountryEntity createOrUpdateCountry(CountryEntity countryEntity) {
        return countryService.createOrUpdateCountry(countryEntity);
    }

    @GetMapping(path = "/getCountry",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<CountryEntity> getCountry(
            @RequestParam("countryCode")int countryCode
    ) {
        final CountryEntity countryEntity =countryService.getCountryByCode(countryCode);
        return ResponseEntity.ok(countryEntity);
    }
    @GetMapping(path = "/getAllCountries",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<CountryEntity>> getAllCountries(
    ) {
        final List<CountryEntity> countryEntity =countryService.getAllCountry();
        return ResponseEntity.ok(countryEntity);
    }
}
