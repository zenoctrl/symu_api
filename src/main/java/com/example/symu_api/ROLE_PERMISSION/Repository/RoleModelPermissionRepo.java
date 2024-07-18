package com.example.symu_api.ROLE_PERMISSION.Repository;

import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleModelPermissionRepo extends JpaRepository<RolePermissionModel,Integer> {
    RolePermissionModel getAllByCode(int code);
    List<RolePermissionModel> getAllByRlpmRoleCode(int roleCode);
}
