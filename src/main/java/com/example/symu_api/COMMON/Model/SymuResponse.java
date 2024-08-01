package com.example.symu_api.COMMON.Model;

import lombok.Data;

@Data
public class SymuResponse<T>{
    private String statusCode;
    private String message;
    private T data;
}
