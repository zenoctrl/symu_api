package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class GetAllStockDto {
    private String companyCode;
    private String statusShortDesc;
    private String stockCountryCode;
    private String stockRegionCode;
    private String stockBranchCode;
    private String stockClusterCode;
}
