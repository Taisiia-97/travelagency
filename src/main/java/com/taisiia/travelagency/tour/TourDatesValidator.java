package com.taisiia.travelagency.tour;

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
