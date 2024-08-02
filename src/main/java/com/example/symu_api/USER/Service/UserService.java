package com.example.symu_api.USER.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Model.UserModel;

import java.util.List;

public interface UserService {
    SymuResponse createOrUpdateUser(UserEntity userDao);
    SymuResponse getAllByCode(int code) ;

    SymuResponse getAllById(int idNo);

    SymuResponse getAllByUserRoleCode(int roleCode);

    SymuResponse getAllByUserBrnCode(int brnCode);

    SymuResponse getAllByUserIdAndUserPassword(int idNo, String password);
}
