package com.example.symu_api.COMMON.Model;

import lombok.Data;

import java.util.List;
@Data
public class SymuBulkResponse<T> {
    private String statusCode;
    private String message;
    private String success;
    private String failed;
    private T data;
    private List<SymuErrorInfo> symuErrorInfoList;
}
