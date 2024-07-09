package com.example.symu_api.COMPANY.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMP_CODE")
    private Integer code;
    @Column(name = "COMP_NAME")
    private String compName;
    @Column(name = "COMP_EMAIL")
    private String compEmail;
    @Column(name = "COMP_PHONE")
    private String compPhone;
    @Column(name = "COMP_SHORT_DESC")
    private String compShortDesc;
    @Column(name = "COMP_DESC")
    private String compDesc;
    @Column(name = "COMP_STATUS")
    private String compStatus;
}
