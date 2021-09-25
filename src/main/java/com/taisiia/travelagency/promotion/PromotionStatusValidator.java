package com.taisiia.travelagency.promotion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PromotionStatusValidator implements ConstraintValidator<PromotionStatusValid, PromotionForm> {
    @Override
    public boolean isValid(PromotionForm promotionForm, ConstraintValidatorContext constraintValidatorContext) {

        return PromotionStatus.containsValue(promotionForm.getStatus());
    }
}
