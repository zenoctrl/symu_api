package com.example.symu_api.STOCK.Model;

import lombok.Data;

@Data
public class StockDetailsRes {
    private String stockImei;
    private double stockSellingPrice;
    private String stockDefaulted;
    private String stockModelName;
    private String stockCustomerName;
    private String stockAgentName;
    private String stockBranchName;
    private String stockDealerShipName;
}
