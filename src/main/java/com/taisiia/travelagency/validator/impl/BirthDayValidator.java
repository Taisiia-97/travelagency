package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.dto.UserDto;
import com.taisiia.travelagency.validator.BirthDayValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BirthDayValidator implements ConstraintValidator<BirthDayValid, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return (LocalDate.now().getYear() - userDto.getBirthDay().getYear()) >= 18;
    }
}
