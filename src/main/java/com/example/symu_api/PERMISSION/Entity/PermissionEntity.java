package com.example.symu_api.PERMISSION.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permission")
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERM_CODE")
    private Integer code;
    @Column(name = "PERM_NAME")
    private String permName;
    @Column(name = "PERM_SHORT_DESC")
    private String permShortDesc;
    @Column(name = "PERM_DESCRIPTION")
    private String permDescription;
    @Column(name = "PERM_MIN_LIMIT")
    private double permMinLimit;
    @Column(name = "PERM_MAX_LIMIT")
    private double permMaxLimit;
    @Column(name = "PERM_STATUS")
    private String permStatus;
    @Column(name = "PERM_CREATED_BY")
    private Integer permCreatedBy;
    @Column(name = "PERM_UPDATED_BY")
    private Integer permUpdatedBy;
}
