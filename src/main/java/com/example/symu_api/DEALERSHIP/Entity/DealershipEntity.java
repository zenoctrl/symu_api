package com.example.symu_api.DEALERSHIP.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dealership")
@Data
public class DealershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEALER_CODE")
    private Integer dealerCode;
    @Column(name = "DEALER_COMPANY_CODE")
    private Integer dealerCompanyCode;
    @Column(name = "DEALER_COUNTRY_CODE")
    private Integer dealerCountryCode;
    @Column(name = "DEALER_NAME")
    private String dealerName;
    @Column(name = "DEALER_SHORT_DESC")
    private String dealerShortDec;
    @Column(name = "DEALER_STATUS")
    private String dealerStatus;
}
