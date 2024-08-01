package com.example.symu_api.ROLE_PERMISSION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;

import java.util.List;

public interface RolePermissionService {

    SymuResponse createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto);

    SymuResponse getRolePermissionByCode(int code);
    SymuResponse getRolePermissionByRoleCode(int roleCode);

    SymuResponse getAllRolePermission();
}
