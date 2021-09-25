package com.taisiia.travelagency.hotel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HotelTypeValidator implements ConstraintValidator<HotelTypeValid, HotelForm> {
    @Override
    public boolean isValid(HotelForm hotelForm, ConstraintValidatorContext constraintValidatorContext) {
        return !hotelForm.getHotelType().isBlank() && HotelType.containsValue(hotelForm.getHotelType());
    }
}
