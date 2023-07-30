package com.webstudy.webstudy.service;

import com.webstudy.webstudy.entity.RoleEntity;
import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 유저 저장
    public void saveUser(UserEntity user) {
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        // 권한 부여
        RoleEntity role = new RoleEntity();
        role.setRoleId(1L);
        user.getRoleList().add(role);

        // 날짜 저장
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreateDate(timestamp);

        // 저장
        userRepository.save(user);
    }

    // 모든 유저 조회
    public List<UserEntity> getUserList() {
        return userRepository.findAll();
    }

    // 아이디를 통해 특정 유저 조회
    public UserEntity getUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    // 이름을 통해 특정 유저 조회
    public UserEntity getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
