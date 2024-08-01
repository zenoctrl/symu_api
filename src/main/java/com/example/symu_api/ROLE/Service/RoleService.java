package com.example.symu_api.ROLE.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.ROLE.Entity.RoleEntity;
import com.example.symu_api.ROLE.Model.RoleModel;

import java.util.List;

public interface RoleService {
    SymuResponse createOrUpdateRole(RoleEntity RoleDto);

    SymuResponse getRoleByRoleCode(int roleCode);

    SymuResponse getAllRoles();

}
