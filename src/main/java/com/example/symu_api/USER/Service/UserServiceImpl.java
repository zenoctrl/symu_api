package com.example.symu_api.USER.Service;

import com.example.symu_api.USER.Entity.UserEntity;
import com.example.symu_api.USER.Model.UserModel;
import com.example.symu_api.USER.Repository.UserModelRepo;
import com.example.symu_api.USER.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserModelRepo userModelRepo;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity createOrUpdateUser(UserEntity userDao) {
        UserEntity userEntity=new UserEntity();
        try {
            UserEntity userEntityData=userRepository.getAllByCode(userDao.getCode());
            userEntity.setCode(userEntityData.getCode());
        }catch (Exception e){
            //new user
        }
        userEntity.setUserFirstName(userDao.getUserFirstName());
        userEntity.setUserLastName(userDao.getUserLastName());
        userEntity.setUserEmail(userDao.getUserEmail());
        userEntity.setUserPhone(userDao.getUserPhone());
        userEntity.setUserId(userDao.getUserId());
        userEntity.setUserHudumaNo(userDao.getUserHudumaNo());
        userEntity.setUserPassword(userDao.getUserPassword());
        userEntity.setUserRoleCode(userDao.getUserRoleCode());
        userEntity.setUserCompCode(userDao.getUserCompCode());
        userEntity.setUserBrnCode(userDao.getUserBrnCode());
        userEntity.setUserCountryCode(userDao.getUserCountryCode());
        UserEntity entitySaved=userRepository.save(userEntity);
        return entitySaved;
    }
    @Override
    public UserEntity getAllByCode(int code) {
        return userRepository.getAllByCode(code);
    }

    @Override
    public UserEntity getAllById(int idNo) {
        return userRepository.getAllByUserId(idNo);
    }

    @Override
    public List<UserEntity> getAllByUserRoleCode(int roleCode) {
        return userRepository.getAllByUserRoleCode(roleCode);
    }

    @Override
    public List<UserEntity> getAllByUserBrnCode(int brnCode) {
        return userRepository.getAllByUserBrnCode(brnCode);
    }

    @Override
    public UserModel getAllByUserIdAndUserPassword(int idNo, String password) {
        return userModelRepo.getAllByUserIdAndUserPassword(idNo,password);
    }
}
