package com.example.symu_api.ROLE.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.ROLE.Entity.RoleEntity;
import com.example.symu_api.ROLE.Model.RoleModel;
import com.example.symu_api.ROLE.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/createOrUpdateRole")
    public SymuResponse createOrUpdateRole(@RequestBody RoleEntity roleDto) {
        return roleService.createOrUpdateRole(roleDto);
    }
    @GetMapping(path = "/getAllRoles",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllRoles(
    ) {
        final SymuResponse roleEntityList =roleService.getAllRoles();
        return ResponseEntity.ok(roleEntityList);
    }
    @GetMapping(path = "/getRoleDetails",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getRoleDetails(
            @RequestParam("roleCode")int roleCode
    ) {
        final SymuResponse roleEntity = roleService.getRoleByRoleCode(roleCode);
        return ResponseEntity.ok(roleEntity);
    }
}
