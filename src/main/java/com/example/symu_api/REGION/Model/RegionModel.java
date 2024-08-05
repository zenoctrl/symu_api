package com.example.symu_api.REGION.Model;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "region")
@Data
public class RegionModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "REGION_CODE")
    private Integer code;
    @Column(name = "REGION_COMP_CODE")
    private Integer regionCompanyCode;
    @Column(name = "REGION_COUNTRY_CODE")
    private Integer regionCountryCode;
    @Column(name = "REGION_SHT_DESC")
    private String regionShortDesc;
    @Column(name = "REGION_NAME")
    private String regionName;
    @Column(name = "REGION_DESC")
    private String regionDesc;
    @Column(name = "REGION_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "REGION_COUNTRY_CODE",insertable = false,updatable = false)
    private CountryEntity countryEntity;
}
