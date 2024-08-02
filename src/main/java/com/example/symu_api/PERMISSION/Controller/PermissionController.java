package com.example.symu_api.PERMISSION.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.PERMISSION.Entity.PermissionEntity;
import com.example.symu_api.PERMISSION.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/createOrUpdatePermission")
    public SymuResponse createOrUpdatePermission(@RequestBody PermissionEntity permissionDto) {
        return permissionService.createOrUpdatePermission(permissionDto);
    }
    @GetMapping(path = "/getAllPermission",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllPermission(
    ) {
        final SymuResponse permissionEntityList =permissionService.getAllPermissions();
        return ResponseEntity.ok(permissionEntityList);
    }
    @GetMapping(path = "/getPermissionByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getPermissionByCode(
            @RequestParam("code")int code
    ) {
        final SymuResponse permissionEntity = permissionService.getPermissionByCode(code);
        return ResponseEntity.ok(permissionEntity);
    }
}
