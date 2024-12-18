package com.example.symu_api.STOCK.Model;

import lombok.Data;

@Data
public class StockDetailsRes {
    private int stockCode;
    private String stockImei;
    private String stockModelName;
    private String stockCurrencyCode;
    private double stockSellingPrice;
    private String stockCustomerName;
    private String stockCustomerPhone;
    private String stockCustomerNationalId;
    private String stockAgentName;
    private String stockDealerShipName;
    private String stockBranchName;
    private String stockDefaulted;
    private String stockBranchCode;
    private String stockRegionCode;
    private String stockCountryCode;
    private String stockCreatedOn;
    private String stockUpdatedOn;
    private String stockBatchNo;
    private int stockClusterCode;
    private String stockClusterName;
}
