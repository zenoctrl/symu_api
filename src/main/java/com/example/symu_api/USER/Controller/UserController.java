package com.example.symu_api.USER.Controller;

import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Model.UserModel;
import com.example.symu_api.USER.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symu/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createOrUpdateUser")
    public UserEntity createOrUpdateUser(UserEntity userDao) {
        return userService.createOrUpdateUser(userDao);
    }
    @GetMapping(path = "/getUserByUserCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<UserEntity> getUserByUserCode(
            @RequestParam("userCode")int userCode
    ) {
        final UserEntity userEntity =userService.getAllByCode(userCode);
        return ResponseEntity.ok(userEntity);
    }
    @GetMapping(path = "/getAllById",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<UserEntity> getAllById(
            @RequestParam("userCode")int userCode
    ) {
        final UserEntity userEntity =userService.getAllById(userCode);
        return ResponseEntity.ok(userEntity);
    }
    @GetMapping(path = "/getAllByUserRoleCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<UserEntity>> getAllByUserRoleCode(
            @RequestParam("roleCode")int roleCode
    ) {
        final List<UserEntity> userEntityList =userService.getAllByUserRoleCode(roleCode);
        return ResponseEntity.ok(userEntityList);
    }
    @GetMapping(path = "/getAllByUserBrnCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<List<UserEntity>> getAllByUserBrnCode(
            @RequestParam("brnCode")int brnCode
    ) {
        final List<UserEntity> userEntityList =userService.getAllByUserBrnCode(brnCode);
        return ResponseEntity.ok(userEntityList);
    }
    @GetMapping(path = "/login",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<UserModel> getAllByUserBrnCode(
            @RequestParam("userId")int userId,
            @RequestParam("password")String password
    ) {
        final UserModel user=userService.getAllByUserIdAndUserPassword(userId,password);
        return ResponseEntity.ok(user);
    }
}
