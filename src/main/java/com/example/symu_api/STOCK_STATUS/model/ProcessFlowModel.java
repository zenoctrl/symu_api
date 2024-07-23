package com.example.symu_api.STOCK_STATUS.model;

import lombok.Data;

@Data
public class ProcessFlowModel {

    private Integer orderStatus;
    private String orderStatusShtDesc;
    private String orderStatusDec;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusShtDesc() {
        return orderStatusShtDesc;
    }

    public void setOrderStatusShtDesc(String orderStatusShtDesc) {
        this.orderStatusShtDesc = orderStatusShtDesc;
    }

    public String getOrderStatusDec() {
        return orderStatusDec;
    }

    public void setOrderStatusDec(String orderStatusDec) {
        this.orderStatusDec = orderStatusDec;
    }
}
