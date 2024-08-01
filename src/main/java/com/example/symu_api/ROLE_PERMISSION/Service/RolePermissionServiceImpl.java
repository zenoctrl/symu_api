package com.example.symu_api.ROLE_PERMISSION.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
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
    public SymuResponse createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto) {
        SymuResponse symuResponse=new SymuResponse<>();
      try{
          RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
          try{
              RolePermissionEntity rolePermissionEntityData=rolePermissionRepo.getAllByCode(rolePermissionDto.getCode());
              rolePermissionEntity.setCode(rolePermissionEntityData.getCode());
          }catch (Exception e){
              //new role permission
          }
          rolePermissionEntity.setRolePermissionRoleCode(rolePermissionDto.getRolePermissionRoleCode());
          rolePermissionEntity.setRolePermissionPermissionCode(rolePermissionDto.getRolePermissionPermissionCode());
          rolePermissionEntity.setRolePermissionStatus(rolePermissionDto.getRolePermissionStatus());
          rolePermissionEntity.setRolePermissionCreatedBy(rolePermissionDto.getRolePermissionCreatedBy());
          rolePermissionEntity.setRolePermissionUpdatedBy(rolePermissionDto.getRolePermissionUpdatedBy());
          RolePermissionEntity rolePermissionEntity1= rolePermissionRepo.save(rolePermissionEntity);

          symuResponse.setStatusCode("0");
          symuResponse.setMessage("Success");
          symuResponse.setData(rolePermissionEntity1);
      }catch (Exception e){
          symuResponse.setStatusCode("1");
          symuResponse.setMessage("failed");
          symuResponse.setData(e.getMessage());
      }
      return symuResponse;
    }

    @Override
    public SymuResponse getRolePermissionByCode(int code) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            RolePermissionModel rolePermissionModel=roleModelPermissionRepo.getAllByCode(code);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(rolePermissionModel);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public  SymuResponse getRolePermissionByRoleCode(int roleCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<RolePermissionModel> rolePermissionModelList=roleModelPermissionRepo.getAllByRolePermissionRoleCode(roleCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(rolePermissionModelList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllRolePermission() {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            List<RolePermissionModel> rolePermissionModelList=roleModelPermissionRepo.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(rolePermissionModelList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
