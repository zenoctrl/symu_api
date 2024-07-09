package com.example.symu_api.ROLE_PERMISSION.Controller;

import com.example.symu_api.ROLE_PERMISSION.Entity.RolePermissionEntity;
import com.example.symu_api.ROLE_PERMISSION.Service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/rolePermission")
public class RolePermissionController {
@Autowired
private RolePermissionService rolePermissionService;

    @PostMapping("/createOrUpdateRolePermission")
    public RolePermissionEntity createOrUpdateRolePermission(RolePermissionEntity rolePermissionDto) {
        return rolePermissionService.createOrUpdateRolePermission(rolePermissionDto);
    }
    @GetMapping(path = "/getAllRolePermission",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<RolePermissionEntity>> getAllRolePermission(
    ) {
        final List<RolePermissionEntity> rolePermissionEntityList =rolePermissionService.getAllRolePermission();
        return ResponseEntity.ok(rolePermissionEntityList);
    }
    @GetMapping(path = "/getRolePermissionByRoleCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<RolePermissionEntity>> getRolePermissionByRoleCode(
            @RequestParam("roleCode")int roleCode
    ) {
        final List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.getRolePermissionByRoleCode(roleCode);
        return ResponseEntity.ok(rolePermissionEntityList);
    }
    @GetMapping(path = "/getRolePermissionByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<RolePermissionEntity> getRolePermissionByCode(
            @RequestParam("code")int code
    ) {
        final RolePermissionEntity rolePermissionEntity = rolePermissionService.getRolePermissionByCode(code);
        return ResponseEntity.ok(rolePermissionEntity);
    }
}
