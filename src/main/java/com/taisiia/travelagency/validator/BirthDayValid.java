package com.taisiia.travelagency.validator;

import com.taisiia.travelagency.validator.impl.BirthDayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthDayValidator.class)
public @interface BirthDayValid {
    String message() default "You can't create account if you are have less than 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
