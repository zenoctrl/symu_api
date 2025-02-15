package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class StockDetailsDto {
    private int companyCode;
    private int stockCountryCode;
    private int stockRegionCode;
    private int stockBranchCode;
    private int stockClusterCode;
}
