package com.example.symu_api.PROCESS_FLOW.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "process_flow")
@Data
public class ProcessFlowEntity {
    @Id
    @Column(name = "PFLW_ID")
    private Integer pflwId;
    @Column(name = "PFLW_CODE")
    private Integer pflwCode;
    @Column(name = "PFLW_SHT_DESC")
    private String pflwShtDesc;
    @Column(name = "PFLW_DESC")
    private String pflwsDec;
}
