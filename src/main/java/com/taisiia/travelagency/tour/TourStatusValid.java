package com.taisiia.travelagency.tour;

import com.taisiia.travelagency.hotel.HotelTypeValidator;

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
