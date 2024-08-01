package com.example.symu_api.STOCK_BATCH.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "stock_batch")
@Data
public class StockBatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BATCH_CODE")
    private Integer code;
    @Column(name = "BATCH_NAME")
    private String batchName;
    @Column(name = "BATCH_SHORT_DESC")
    private String batchShortDesc;
    @Column(name = "BATCH_DESCRIPTION")
    private String batchDescription;
    @Column(name = "BATCH_STATUS")
    private String batchStatus;
    @Column(name = "BATCH_DATE")
    private Date batchDate;
}
