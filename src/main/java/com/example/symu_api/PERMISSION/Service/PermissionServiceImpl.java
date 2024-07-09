package com.example.symu_api.PERMISSION.Service;

import com.example.symu_api.PERMISSION.Entity.PermissionEntity;
import com.example.symu_api.PERMISSION.Repository.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    public PermissionEntity createOrUpdatePermission(PermissionEntity permissionDto) {
        PermissionEntity permissionEntity=new PermissionEntity();
        try{
            PermissionEntity permissionEntityData=permissionRepo.getAllByCode(permissionDto.getCode());
            permissionEntity.setCode(permissionEntityData.getCode());
        }catch (Exception e){
            // new permission
            permissionEntity.setPermCreatedBy(permissionDto.getPermCreatedBy());
        }
        permissionEntity.setPermName(permissionDto.getPermName());
        permissionEntity.setPermShortDesc(permissionDto.getPermShortDesc());
        permissionEntity.setPermDescription(permissionDto.getPermDescription());
        permissionEntity.setPermMinLimit(permissionDto.getPermMinLimit());
        permissionEntity.setPermMaxLimit(permissionDto.getPermMaxLimit());
        permissionEntity.setPermStatus(permissionDto.getPermStatus());
        permissionEntity.setPermStatus(permissionDto.getPermStatus());
        permissionEntity.setPermUpdatedBy(permissionDto.getPermUpdatedBy());

        PermissionEntity permissionEntitySave=permissionRepo.save(permissionEntity);
        return permissionEntitySave;
    }

    @Override
    public PermissionEntity getPermissionByCode(int code) {
        return permissionRepo.getAllByCode(code);
    }

    @Override
    public List<PermissionEntity> getAllPermissions() {
        return permissionRepo.findAll();
    }
}
