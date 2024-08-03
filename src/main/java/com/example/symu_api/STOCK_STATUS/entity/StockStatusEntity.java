package com.example.symu_api.STOCK_STATUS.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "stock_status")
@Data
public class StockStatusEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "STATUS_CODE")
    private Integer statusCode;
    @Column(name = "STATUS_NAME")
    private String statusName;
    @Column(name = "STATUS_SHORT_DESC")
    private String statusShortDesc;
    @Column(name = "STATUS_DESCRIPTION")
    private String statusDescription;
}
