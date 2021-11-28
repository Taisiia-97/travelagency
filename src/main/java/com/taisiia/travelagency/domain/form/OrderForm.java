package com.taisiia.travelagency.domain.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForm {
    @NotBlank
    private String tourUuid;
    @NotNull
    @Positive
    private Integer adultsPlaces;
    @PositiveOrZero
    private Integer childPlaces;
    private String discountCode;
}
