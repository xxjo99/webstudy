package com.webstudy.webstudy.validator;

import com.webstudy.webstudy.entity.BoardEntity;
import com.webstudy.webstudy.entity.UserEntity;
import com.webstudy.webstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserEntity user = (UserEntity) object;

        if(userRepository.existsByUserName(user.getUserName())){
            errors.rejectValue("userName", "invalid.email",
                    new Object[]{user.getUserName()}, "이미 사용중인 아이디입니다.");
        }

    }

}
