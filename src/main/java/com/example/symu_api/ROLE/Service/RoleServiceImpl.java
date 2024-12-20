package com.example.symu_api.ROLE.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
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
    public SymuResponse createOrUpdateRole(RoleEntity roleDto) {
        SymuResponse symuResponse = new SymuResponse<>();
        try {
                RoleEntity roleEntitySaved = roleRepository.save(roleDto);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(roleEntitySaved);

        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getRoleByRoleCode(int roleCode) {
        SymuResponse symuResponse = new SymuResponse<>();
        try {
            RoleModel roleEntitySaved = roleModelRepository.getAllByCode(roleCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(roleEntitySaved);
        } catch (Exception e) {
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
        @Override
        public SymuResponse getAllRoles () {
            SymuResponse symuResponse = new SymuResponse<>();
        try{
            List<RoleModel> roleModelList= roleModelRepository.findAll();
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("success");
            symuResponse.setData(roleModelList);
        }catch (Exception e){
            symuResponse.setStatusCode("1");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
            return symuResponse;
        }
    }
