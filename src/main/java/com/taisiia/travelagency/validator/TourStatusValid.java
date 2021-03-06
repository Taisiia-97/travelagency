package com.taisiia.travelagency.validator;

import com.taisiia.travelagency.validator.impl.TourStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TourStatusValidator.class)
public @interface TourStatusValid {
    String message() default "Tour type is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
