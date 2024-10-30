package com.example.symu_api.COMMON.Model;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class SymuResponse<T>{
    private String statusCode;
    private String message;
    private T data;
}
