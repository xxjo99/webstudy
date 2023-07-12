package com.webstudy.webstudy.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserEntity user) {
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        // 권한 부여
        RoleEntity role = new RoleEntity();
        role.setRoleId(1L);
        user.getRoleList().add(role);
        
        // 저장
        userRepository.save(user);
    }


}
