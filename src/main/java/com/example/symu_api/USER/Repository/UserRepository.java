package com.example.symu_api.USER.Repository;

import com.example.symu_api.USER.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity getAllByCode(int code) ;

    UserEntity getAllByUserId(int idNo);

    List<UserEntity> getAllByUserRoleCode(int roleCode);

    List<UserEntity> getAllByUserBrnCode(int brnCode);

    UserEntity getAllByUserIdAndUserPassword(int idNo,String password);
}
