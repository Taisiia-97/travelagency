package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.form.TourForm;
import com.taisiia.travelagency.validator.TourDatesValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TourDatesValidator implements ConstraintValidator<TourDatesValid, TourForm> {
    @Override
    public boolean isValid(TourForm tourForm, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime departureDate = tourForm.getDepartureDate();
        LocalDateTime returnDate = tourForm.getReturnDate();
        return departureDate.isBefore(returnDate) && departureDate.isAfter(LocalDateTime.now().plusDays(3));
    }
}
