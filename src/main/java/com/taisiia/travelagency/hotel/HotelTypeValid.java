package com.taisiia.travelagency.hotel;

import com.taisiia.travelagency.promotion.PromotionStatusValidator;
import com.taisiia.travelagency.tour.TourStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HotelTypeValidator.class)
public @interface HotelTypeValid {
    String message() default "Hotel type is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
