package com.example.symu_api.RECEIPT.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Data
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RCT_CODE")
    private Integer receiptCode;
    @Column(name = "RCT_COMP_CODE")
    private Integer receiptCompanyCode;
    @Column(name = "RCT_COUNTRY_CODE")
    private Integer receiptCountryCode;
    @Column(name = "RCT_REGION_CODE")
    private Integer receiptRegionCode;
    @Column(name = "RCT_BRN_CODE")
    private Integer receiptBranchCode;
    @Column(name = "RCT_RECEIPT_NO")
    private String receiptNo;
    @Column(name = "RCT_CREATED_ON")
    private LocalDateTime receiptCreatedOn;
    @Column(name = "RCT_UPDATED_ON")
    private LocalDateTime receiptUpdatedOn;
    @Column(name = "RCT_CREATED_BY")
    private String receiptCreatedBy;
    @Column(name = "RCT_UPDATED_BY")
    private String receiptUpdatedBy;
    @Column(name = "RCT_CUSTOMER_NAME")
    private String receiptCustomerName;
    @Column(name = "RCT_CUSTOMER_PHONE")
    private String receiptCustomerPhoneNo;
    @Column(name = "RCT_CUSTOMER_ID")
    private String receiptCustomerIdNo;
    @Column(name = "RCT_AGENT_NAME")
    private String receiptAgentName;
    @Column(name = "RCT_AGENT_PHONE")
    private String receiptAgentPhoneNo;
    @Column(name = "RCT_AGENT_ID")
    private String receiptAgentIdNo;
    @Column(name = "RCT_STOCK_CODE")
    private Integer receiptStockCode;
    @Column(name = "RCT_STOCK_IMEI")
    private String receiStockImei;
    @Column(name = "RCT_ITEM_QUANTITY")
    private double receiStockQuantity;
    @Column(name = "RCT_AMOUNT")
    private double receiptAmount;
    @Column(name = "RCT_NET_AMOUNT")
    private double receiptNetAmount;
    @Column(name = "RCT_GROSS_AMOUNT")
    private double receiptGrossAmount;
    @Column(name = "RCT_COMMISSION_AMOUNT")
    private double receiptCommissionAmount;
    @Column(name = "RCT_MODEL")
    private String receiptModel;
    @Column(name = "RCT_STATUS")
    private String receiptStatus;
    @Column(name = "RCT_DEALERSHIP")
    private String receiptDealership;
    @Column(name = "RCT_CLUSTER_CODE")
    private Integer receiptClusterCode;
}
