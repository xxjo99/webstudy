package com.webstudy.webstudy.validator;

import com.webstudy.webstudy.entity.BoardEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BoardEntity board = (BoardEntity) obj;

        if (StringUtils.isEmpty(board.getTitle())) {
            errors.rejectValue("title", "key", "제목을 입력해주세요.");
        }

        if (StringUtils.isEmpty(board.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력해주세요.");
        }

    }

}
