package com.taisiia.travelagency.tour;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TourStatusValidator implements ConstraintValidator<TourStatusValid, TourForm> {
    @Override
    public boolean isValid(TourForm tourForm, ConstraintValidatorContext constraintValidatorContext) {
        return TourStatus.containsValue(tourForm.getStatus());
    }
}
