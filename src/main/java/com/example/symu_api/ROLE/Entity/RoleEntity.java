package com.example.symu_api.ROLE.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class RoleEntity {
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
}
