package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class StockDetailsDto {
    private String companyCode;
    private String stockCountryCode;
    private String stockRegionCode;
    private String stockBranchCode;
    private String stockClusterCode;
    private String stockDateFrom;
    private String stockDateTo;
}
