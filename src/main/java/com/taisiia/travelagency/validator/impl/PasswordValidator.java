package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.dto.UserDto;
import com.taisiia.travelagency.validator.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<PasswordValid, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
