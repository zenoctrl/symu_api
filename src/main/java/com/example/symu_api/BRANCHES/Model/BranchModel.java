package com.example.symu_api.BRANCHES.Model;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import com.example.symu_api.REGION.Entity.RegionEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "branches")
@Data
public class BranchModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BRN_CODE")
    private Integer code;
    @Column(name = "BRN_COMP_CODE")
    private Integer companyCode;
    @Column(name = "BRN_COUNTRY_CODE")
    private Integer countryCode;
    @Column(name = "BRN_REGION_CODE")
    private Integer regionCode;
    @Column(name = "BRN_SHT_DESC")
    private String shortDesc;
    @Column(name = "BRN_NAME")
    private String name;
    @Column(name = "BRN_DESC")
    private String desc;
    @Column(name = "BRN_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "BRN_COUNTRY_CODE",insertable = false,updatable = false)
    private CountryEntity countryEntity;

    @ManyToOne
    @JoinColumn(name = "BRN_REGION_CODE",insertable = false,updatable = false)
    private RegionEntity regionEntity;
}
