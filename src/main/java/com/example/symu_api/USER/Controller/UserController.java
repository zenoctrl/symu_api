package com.example.symu_api.USER.Controller;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Model.UserModel;
import com.example.symu_api.USER.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createOrUpdateUser")
    public SymuResponse createOrUpdateUser(@RequestBody UserEntity userDao) {
        return userService.createOrUpdateUser(userDao);
    }
    @GetMapping(path = "/getUserByUserCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getUserByUserCode(
            @RequestParam("userCode")int userCode
    ) {
        final SymuResponse userEntity =userService.getAllByCode(userCode);
        return ResponseEntity.ok(userEntity);
    }
    @GetMapping(path = "/getAllById",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllById(
            @RequestParam("userCode")int userCode
    ) {
        final SymuResponse userEntity =userService.getAllById(userCode);
        return ResponseEntity.ok(userEntity);
    }
    @GetMapping(path = "/getAllByUserRoleCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllByUserRoleCode(
            @RequestParam("roleCode")int roleCode
    ) {
        final SymuResponse userEntityList =userService.getAllByUserRoleCode(roleCode);
        return ResponseEntity.ok(userEntityList);
    }
    @GetMapping(path = "/getAllByUserBranchCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllByUserBranchCode(
            @RequestParam("brnCode")int brnCode
    ) {
        final SymuResponse userEntityList =userService.getAllByUserBrnCode(brnCode);
        return ResponseEntity.ok(userEntityList);
    }
    @GetMapping(path = "/login",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllByUserBrnCode(
            @RequestParam("userId")int userId,
            @RequestParam("password")String password
    ) {
        final SymuResponse user=userService.getAllByUserIdAndUserPassword(userId,password);
        return ResponseEntity.ok(user);
    }
}
