package com.example.symu_api.ROLE.Model;

import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_CODE")
    private Integer code;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_SHORT_DESC")
    private String roleShortDesc;
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
    @Column(name = "ROLE_STATUS")
    private String roleStatus;
    @Column(name = "ROLE_CREATED_BY")
    private Integer roleCreatedBy;
    @Column(name = "ROLE_UPDATED_BY")
    private Integer roleUpdatedBy;

    @OneToMany(mappedBy = "rlpmRoleCode")
    private Set<RolePermissionModel> rolePermissionModels;
}
