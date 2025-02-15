package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class StockDetailsDto {
    private Integer companyCode;
    private Integer stockCountryCode;
    private Integer stockRegionCode;
    private Integer stockBranchCode;
    private Integer stockClusterCode;
}
