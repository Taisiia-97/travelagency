package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.form.HotelForm;
import com.taisiia.travelagency.enums.HotelType;
import com.taisiia.travelagency.validator.HotelTypeValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HotelTypeValidator implements ConstraintValidator<HotelTypeValid, HotelForm> {
    @Override
    public boolean isValid(HotelForm hotelForm, ConstraintValidatorContext constraintValidatorContext) {
        return !hotelForm.getHotelType().isBlank() && HotelType.containsValue(hotelForm.getHotelType());
    }
}
