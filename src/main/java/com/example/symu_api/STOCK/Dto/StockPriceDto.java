package com.example.symu_api.STOCK.Dto;

import lombok.Data;

@Data
public class StockPriceDto {
    private int stockCode;
    private int userCode;
    private int statusCode;
    private double buyingPrice;
    private double sellingPrice;
}
