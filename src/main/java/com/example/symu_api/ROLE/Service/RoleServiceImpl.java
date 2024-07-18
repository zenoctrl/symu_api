package com.example.symu_api.ROLE.Service;

import com.example.symu_api.ROLE.Entity.RoleEntity;
import com.example.symu_api.ROLE.Model.RoleModel;
import com.example.symu_api.ROLE.Repository.RoleModelRepository;
import com.example.symu_api.ROLE.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleModelRepository roleModelRepository;

    @Override
    public RoleEntity createOrUpdateRole(RoleEntity roleDto) {
        RoleEntity roleEntity = new RoleEntity();
        try {
            RoleEntity roleEntityData =roleRepository.getAllByCode(roleEntity.getCode());
            roleEntity.setCode(roleEntityData.getCode());
        } catch (Exception e) {
            // new role
        }
        roleEntity.setRoleName(roleDto.getRoleName());
        roleEntity.setRoleShortDesc(roleDto.getRoleShortDesc());
        roleEntity.setRoleDescription(roleDto.getRoleDescription());
        roleEntity.setRoleStatus(roleDto.getRoleStatus());
        roleEntity.setRoleCreatedBy(roleDto.getRoleCreatedBy());
        roleEntity.setRoleUpdatedBy(roleDto.getRoleUpdatedBy());

        RoleEntity roleEntitySaved=roleRepository.save(roleEntity);
        return roleEntitySaved;
    }

    @Override
    public RoleModel getRoleByRoleCode(int roleCode) {
        return roleModelRepository.getAllByCode(roleCode);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return roleModelRepository.findAll();
    }
}
