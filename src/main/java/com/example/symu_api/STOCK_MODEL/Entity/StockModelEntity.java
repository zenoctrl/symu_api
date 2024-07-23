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
    @Column(name = "MODEL_NAME")
    private String modelName;
    @Column(name = "MODEL_SHORT_DESC")
    private String modelShortDesc;
    @Column(name = "MODEL_DESCRIPTION")
    private String modelDescription;
    @Column(name = "MODEL_STATUS")
    private String modelStatus;
}
