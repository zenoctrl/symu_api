package com.example.symu_api.STOCK_MODEL.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock_model")
@Data
public class StockModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODEL_CODE")
    private Integer code;
    @Column(name = "MODEL_COMP_CODE")
    private Integer modelCompanyCode;
    @Column(name = "MODEL_COUNTRY_CODE")
    private Integer modelCountryCode;
    @Column(name = "MODEL_REGION_CODE")
    private Integer modelRegionCode;
    @Column(name = "MODEL_BRN_CODE")
    private Integer modelBranchCode;
    @Column(name = "MODEL_NAME")
    private String modelName;
    @Column(name = "MODEL_SHORT_DESC")
    private String modelShortDesc;
    @Column(name = "MODEL_DESCRIPTION")
    private String modelDescription;
    @Column(name = "MODEL_STATUS")
    private String modelStatus;
    @Column(name = "MODEL_SELLING_PRICE")
    private Double modelSellingPrice;
}
