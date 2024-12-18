package com.example.symu_api.STOCK.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateStockBulkDto {
    private int stockClusterCode;
    private int stockBatchCode;
    private List<String> stockImei;
    private int stockModelCode;
    private int stockCreatedBy;
}
