package com.taisiia.travelagency.tour;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TourDatesValidator.class)
public @interface TourDatesValid {
    String message() default "Dates are  not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
