package com.example.symu_api.PERMISSION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
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
    public SymuResponse createOrUpdatePermission(PermissionEntity permissionDto) {
        SymuResponse symuResponse=new SymuResponse<>();
       try {
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

           symuResponse.setStatusCode("0");
           symuResponse.setMessage("Success");
           symuResponse.setData(permissionEntitySave);

       }catch (Exception e) {
           symuResponse.setStatusCode("1");
           symuResponse.setMessage("failed");
           symuResponse.setData(e.getMessage());
       }
       return symuResponse;
    }

    @Override
    public SymuResponse getPermissionByCode(int code) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            PermissionEntity permissionEntity=permissionRepo.getAllByCode(code);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(permissionEntity);

        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllPermissions() {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            List<PermissionEntity> permissionEntityList=permissionRepo.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(permissionEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
