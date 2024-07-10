package com.example.symu_api.USER.Service;

import com.example.symu_api.USER.Entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createOrUpdateUser(UserEntity userDao);
    UserEntity getAllByCode(int code) ;

    UserEntity getAllById(int idNo);

    List<UserEntity> getAllByUserRoleCode(int roleCode);

    List<UserEntity> getAllByUserBrnCode(int brnCode);

    UserEntity getAllByUserIdAndUserPassword(int idNo,String password);
}
