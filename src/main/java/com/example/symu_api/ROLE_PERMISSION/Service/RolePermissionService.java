package com.example.symu_api.ROLE_PERMISSION.Service;

import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;

import java.util.List;

public interface RolePermissionService {

    RolePermissionEntity createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto);

    RolePermissionModel getRolePermissionByCode(int code);
    List<RolePermissionModel> getRolePermissionByRoleCode(int roleCode);

    List<RolePermissionModel> getAllRolePermission();
}
