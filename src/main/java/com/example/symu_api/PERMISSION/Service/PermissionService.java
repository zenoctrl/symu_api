package com.example.symu_api.PERMISSION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.PERMISSION.Entity.PermissionEntity;

import java.util.List;

public interface PermissionService {

    SymuResponse createOrUpdatePermission(PermissionEntity permissionDto);

    SymuResponse getPermissionByCode(int code);

    SymuResponse getAllPermissions();
}
