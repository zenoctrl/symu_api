package com.example.symu_api.PERMISSION.Service;

import com.example.symu_api.PERMISSION.Entity.PermissionEntity;

import java.util.List;

public interface PermissionService {

    PermissionEntity createOrUpdatePermission(PermissionEntity permissionDto);

    PermissionEntity getPermissionByCode(int code);

    List<PermissionEntity> getAllPermissions();
}
