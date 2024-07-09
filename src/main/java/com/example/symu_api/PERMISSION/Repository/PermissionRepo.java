package com.example.symu_api.PERMISSION.Repository;

import com.example.symu_api.PERMISSION.Entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepo extends JpaRepository<PermissionEntity,Integer> {
    PermissionEntity getAllByCode(int code);
}
