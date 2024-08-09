package com.example.symu_api.AGENTS.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "agents")
@Data
public class AgentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGENT_CODE")
    private Integer agentCode;
    @Column(name = "AGENT_COMPANY_CODE")
    private Integer agentCompanyCode;
    @Column(name = "AGENT_COUNTRY_CODE")
    private Integer agentCountryCode;
    @Column(name = "AGENT_REGION_CODE")
    private Integer agentRegionCode;
    @Column(name = "AGENT_BRANCH_CODE")
    private Integer agentBranchCode;
    @Column(name = "AGENT_NAME")
    private String agentName;
    @Column(name = "AGENT_PHONE")
    private String agentPhoneNumber;
    @Column(name = "AGENT_NATIONAL_ID")
    private String agentNationalId;
}
