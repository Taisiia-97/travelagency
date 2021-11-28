package com.taisiia.travelagency.validator.impl;

import com.taisiia.travelagency.domain.form.DiscountForm;
import com.taisiia.travelagency.enums.DiscountStatus;
import com.taisiia.travelagency.validator.DiscountStatusValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DiscountStatusValidator implements ConstraintValidator<DiscountStatusValid, DiscountForm> {
    @Override
    public boolean isValid(DiscountForm promotionForm, ConstraintValidatorContext constraintValidatorContext) {

        return DiscountStatus.containsValue(promotionForm.getStatus());
    }
}
