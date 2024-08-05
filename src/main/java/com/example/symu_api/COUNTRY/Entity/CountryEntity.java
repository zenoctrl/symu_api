package com.example.symu_api.COUNTRY.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "countries")
@Data
public class CountryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CTRY_CODE")
    private Integer code;
    @Column(name = "CTRY_COMPANY_CODE")
    private Integer companyCode;
    @Column(name = "CTRY_SHORT_DESC")
    private String countryShortDesc;
    @Column(name = "CTRY_NAME")
    private String countryName;
    @Column(name = "CTRY_CURRENCY_CODE")
    private String countryCurrencyCode;
    @Column(name = "CTRY_COUNTRY_CODE")
    private String countryCountryCode;
    @Column(name = "CTRY_STATUS")
    private String status;
}
