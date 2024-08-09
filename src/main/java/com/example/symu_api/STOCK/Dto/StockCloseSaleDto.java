package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class StockCloseSaleDto {
    private int stockCode;
    private int userCode;
    private int nextStatusCode;
    private String agentNationalId;
    private String agentName;
    private String agentPhoneNumber;
}
