package com.example.symu_api.USER.Model;

import com.example.symu_api.ROLE.Model.RoleModel;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CODE")
    private Integer code;
    @Column(name = "USER_FIRSTNAME")
    private String userFirstName;
    @Column(name = "USER_LASTNAME")
    private String userLastName;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "USER_PHONE")
    private String userPhone;
    @Column(name = "USER_ID_NUMBER")
    private Integer userId;
    @Column(name = "USER_HUDUMA_NO")
    private Integer userHudumaNo;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "USER_ROLE_CODE")
    private Integer userRoleCode;
    @Column(name = "USER_COMP_CODE")
    private Integer userCompCode;
    @Column(name = "USER_BRN_CODE")
    private Integer userBrnCode;

    @ManyToOne
    @JoinColumn(name = "USER_ROLE_CODE",insertable = false,updatable = false)
    private RoleModel roleModel;
}
