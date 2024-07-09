package com.example.symu_api.ROLE_PERMISSION.Repository;

import com.example.symu_api.PERMISSION.Entity.PermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepo extends JpaRepository<RolePermissionEntity,Integer> {
    RolePermissionEntity getAllByCode(int code);
    List<RolePermissionEntity> getAllByRlpmRoleCode(int roleCode);
}
