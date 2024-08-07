package com.example.symu_api.CUSTOMER.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_CODE")
    private Integer customerCode;
    @Column(name = "CUSTOMER_COMPANY_CODE")
    private Integer customerCompanyCode;
    @Column(name = "CUSTOMER_COUNTRY_CODE")
    private Integer customerCountryCode;
    @Column(name = "CUSTOMER_REGION_CODE")
    private Integer customerRegionCode;
    @Column(name = "CUSTOMER_BRANCH_CODE")
    private Integer customerBranchCode;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_PHONE")
    private String customerPhoneNumber;
    @Column(name = "CUSTOMER_NATIONAL_ID")
    private String customerNationalId;
}
