package com.example.symu_api.USER.Repository;

import com.example.symu_api.USER.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserModelRepo extends JpaRepository<UserModel,Integer> {
    UserModel getAllByCode(int code) ;

    UserModel getAllByUserId(int idNo);

    List<UserModel> getAllByUserRoleCode(int roleCode);

    List<UserModel> getAllByUserBrnCode(int brnCode);

    List<UserModel> getUserModelByUserCompanyCode(int companyCode);

    UserModel getAllByUserIdAndUserPasswordAndUserStatus(int idNo,String password,String status);
}
