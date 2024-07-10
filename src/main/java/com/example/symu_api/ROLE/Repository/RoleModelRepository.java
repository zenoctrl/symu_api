package com.example.symu_api.ROLE.Repository;

import com.example.symu_api.ROLE.Entity.RoleEntity;
import com.example.symu_api.ROLE.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleModelRepository extends JpaRepository<RoleModel,Integer> {
    RoleEntity getAllByCode(int code);
}
