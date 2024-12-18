package com.example.symu_api.STOCK.Model;

import lombok.Data;

@Data
public class StockRes {
    private int code;
    private int stockCompanyCode;
    private int stockCountryCode;
    private int stockRegionCode;
    private int stockBranchCode;
    private int stockBatchCode;
    private int stockAgnCode;
    private String stockImei;
    private int stockModelCode;
    private String stockMemory;
    private double stockBuyingPrice;
    private double stockSellingPrice;
    private double stockProfit;
    private int stockStatusCode;
    private String stockBaseCurrency;
    private String stockDefaulted;
    private int stockCustomerCode;
    private String stockCreatedOn;
    private String stockUpdatedOn;
    private String stockCreatedBy;
    private String stockUpdatedBy;
    private String stockSoldBy;
    private String stockTradeName;
    private int stockDealerCode;
    private String stockStatusDescription;
    private String stockStatusName;
    private String statusShortDesc;
    private String stockBranchName ;
    private String stockCountryName;
    private String stockBatchNumber;
    private int stockClusterCode;
    private String stockClusterName;
}
