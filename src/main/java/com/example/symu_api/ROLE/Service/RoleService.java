package com.example.symu_api.ROLE.Service;

import com.example.symu_api.ROLE.Entity.RoleEntity;

import java.util.List;

public interface RoleService {
    RoleEntity createOrUpdateRole(RoleEntity RoleDto);

    RoleEntity getRoleByRoleCode(int roleCode);

    List<RoleEntity> getAllRoles();

}
