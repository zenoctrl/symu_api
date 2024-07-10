package com.example.symu_api.ROLE_PERMISSION.Service;

import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Model.RolePermissionModel;
import com.example.symu_api.ROLE_PERMISSION.Repository.RoleModelPermissionRepo;
import com.example.symu_api.ROLE_PERMISSION.Repository.RolePermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
    @Autowired
    private RolePermissionRepo rolePermissionRepo;
    @Autowired
    private RoleModelPermissionRepo roleModelPermissionRepo;
    @Override
    public RolePermissionEntity createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto) {
        RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
        try{
            RolePermissionEntity rolePermissionEntityData=rolePermissionRepo.getAllByCode(rolePermissionDto.getCode());
            rolePermissionEntity.setCode(rolePermissionEntityData.getCode());
        }catch (Exception e){
            //new role permission
        }
        rolePermissionEntity.setRlpmRoleCode(rolePermissionDto.getRlpmRoleCode());
        rolePermissionEntity.setRlpmPermCode(rolePermissionDto.getRlpmPermCode());
        rolePermissionEntity.setRlpmStatus(rolePermissionDto.getRlpmStatus());
        rolePermissionEntity.setRlpmCreatedBy(rolePermissionDto.getRlpmCreatedBy());
        rolePermissionEntity.setRlpmUpdatedBy(rolePermissionDto.getRlpmUpdatedBy());
        return rolePermissionRepo.save(rolePermissionEntity);
    }

    @Override
    public RolePermissionEntity getRolePermissionByCode(int code) {
        return rolePermissionRepo.getAllByCode(code);
    }

    @Override
    public  List<RolePermissionEntity> getRolePermissionByRoleCode(int roleCode) {
        return rolePermissionRepo.getAllByRlpmRoleCode(roleCode);
    }

    @Override
    public List<RolePermissionModel> getAllRolePermission() {
        return roleModelPermissionRepo.findAll();
    }
}
