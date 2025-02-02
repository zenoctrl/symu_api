package com.example.symu_api.STOCK_BATCH.Model;

import com.example.symu_api.COUNTRY.Entity.CountryEntity;
import com.example.symu_api.STOCK_MODEL.Entity.StockModelEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "stock_batch")
@Data
public class StockBatchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BATCH_CODE")
    private Integer code;
    @Column(name = "BATCH_COMP_CODE")
    private Integer stockBatchCompanyCode;
    @Column(name = "BATCH_COUNTRY_CODE")
    private Integer stockBatchCountryCode;
    @Column(name = "BATCH_REGION_CODE")
    private Integer stockBatchRegionCode;
    @Column(name = "BATCH_BRANCH_CODE")
    private Integer stockBranchCode;
    @Column(name = "BATCH_MODEL_CODE")
    private Integer stockModelCode;
    @Column(name = "BATCH_NO")
    private String batchNo;
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
    @Column(name = "BATCH_BUYING_PRICE")
    private Double batchBuyingPrice;
    @Column(name = "BATCH_TOTAL_NUMBER")
    private Integer batchTotalNumber;
    @Column(name = "BATCH_TOTAL_ALLOCATED")
    private Integer batchTotalAllocated;
    @Column(name = "BATCH_TOTAL_APPROVED")
    private Integer batchTotalApproved;

    @ManyToOne
    @JoinColumn(name = "BATCH_COUNTRY_CODE",insertable = false,updatable = false)
    private CountryEntity countryEntity;

    @ManyToOne
    @JoinColumn(name = "BATCH_MODEL_CODE",insertable = false,updatable = false)
    private StockModelEntity stockModelEntity;
}
