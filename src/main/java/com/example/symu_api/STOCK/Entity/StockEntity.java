package com.example.symu_api.STOCK.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "stock")
@Data
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_CODE")
    private Integer code;
    @Column(name = "STOCK_COMP_CODE")
    private Integer stockCompanyCode;
    @Column(name = "STOCK_COUNTRY_CODE")
    private Integer stockCountryCode;
    @Column(name = "STOCK_REGION_CODE")
    private Integer stockRegionCode;
    @Column(name = "STOCK_BRN_CODE")
    private Integer stockBranchCode;
    @Column(name = "STOCK_BATCH_CODE")
    private Integer stockBatchCode;
    @Column(name = "STOCK_AGN_CODE")
    private Integer stockAgnCode;
    @Column(name = "STOCK_IMEI")
    private String stockImei;
    @Column(name = "STOCK_MODEL_CODE")
    private Integer stockModelCode;
    @Column(name = "STOCK_MEMORY")
    private String stockMemory;
    @Column(name = "STOCK_BUYING_PRICE")
    private double stockBuyingPrice;
    @Column(name = "STOCK_SELLING_PRICE")
    private double stockSellingPrice;
    @Column(name = "STOCK_PROFIT")
    private double stockProfit;
    @Column(name = "STOCK_STATUS_CODE")
    private Integer stockStatusCode;
    @Column(name = "STOCK_BASE_CURRENCY")
    private String stockBaseCurrency;
    @Column(name = "STOCK_DEFAULTED")
    private String stockDefaulted;
    @Column(name = "STOCK_CUSTOMER_CODE")
    private Integer stockCustomerCode;
    @Column(name = "STOCK_CREATED_ON")
    private LocalDateTime stockCreatedOn;
    @Column(name = "STOCK_UPDATED_ON")
    private LocalDateTime stockUpdatedOn;
    @Column(name = "STOCK_CREATED_BY")
    private Integer stockCreatedBy;
    @Column(name = "STOCK_UPDATED_BY")
    private Integer stockUpdatedBy;
    @Column(name = "STOCK_SOLD_BY")
    private String stockSoldBy;
    @Column(name = "STOCK_TRADE_NAME")
    private String stockTradeName;
    @Column(name = "STOCK_DEALER_CODE")
    private Integer stockDealerCode;
    @Column(name = "STOCK_CLUSTER_CODE")
    private Integer stockClusterCode;
}
