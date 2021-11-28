package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.form.TourForm;
import com.taisiia.travelagency.enums.TourStatus;
import com.taisiia.travelagency.validator.TourStatusValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TourStatusValidator implements ConstraintValidator<TourStatusValid, TourForm> {
    @Override
    public boolean isValid(TourForm tourForm, ConstraintValidatorContext constraintValidatorContext) {
        return TourStatus.containsValue(tourForm.getStatus());
    }
}
