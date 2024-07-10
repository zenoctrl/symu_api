package com.example.symu_api.ROLE_PERMISSION.Service;

import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;

import java.util.List;

public interface RolePermissionService {

    RolePermissionEntity createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto);

    RolePermissionEntity getRolePermissionByCode(int code);
    List<RolePermissionEntity> getRolePermissionByRoleCode(int roleCode);

    List<RolePermissionModel> getAllRolePermission();
}
