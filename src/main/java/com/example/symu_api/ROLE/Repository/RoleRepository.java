package com.example.symu_api.ROLE.Repository;

import com.example.symu_api.ROLE.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity getAllByCode(int code);
}
