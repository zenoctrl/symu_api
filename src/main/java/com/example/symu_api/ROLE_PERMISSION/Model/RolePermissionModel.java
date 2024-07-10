package com.example.symu_api.ROLE_PERMISSION.Model;

import com.example.symu_api.PERMISSION.Entity.PermissionEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role_permission")
@Data
public class RolePermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RLPM_CODE")
    private Integer code;
    @Column(name = "RLPM_ROLE_CODE")
    private Integer rlpmRoleCode;
    @Column(name = "RLPM_PERM_CODE")
    private Integer rlpmPermCode;
    @Column(name = "RLPM_STATUS")
    private String rlpmStatus;
    @Column(name = "RLPM_CREATED_BY")
    private Integer rlpmCreatedBy;
    @Column(name = "RLPM_UPDATED_BY")
    private Integer rlpmUpdatedBy;

    @ManyToOne
    @JoinColumn(name = "RLPM_PERM_CODE",insertable = false,updatable = false)
    private PermissionEntity permissionEntity;

}
