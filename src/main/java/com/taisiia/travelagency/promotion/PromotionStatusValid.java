package com.taisiia.travelagency.promotion;

import com.taisiia.travelagency.promotion.PromotionStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PromotionStatusValidator.class)
public @interface PromotionStatusValid {
    String message() default "Promotion status is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
