package com.example.symu_api.COMMON.Model;

import lombok.Data;

@Data
public class SymuErrorInfo {
    private String statusCode;
    private String statusDesc;
    private String statusMessage;
}
