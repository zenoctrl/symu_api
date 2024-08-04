package com.example.symu_api.REGION.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "region")
@Data
public class RegionEntity {
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
}
