package com.taisiia.travelagency.validator;

import com.taisiia.travelagency.validator.impl.DiscountStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiscountStatusValidator.class)
public @interface DiscountStatusValid {
    String message() default "Promotion status is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
