package com.taisiia.travelagency.domain.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.validator.HotelTypeValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@HotelTypeValid
public class HotelForm {
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+.+")
    private String hotelName;
    @NotNull
    @Positive
    private Integer standard;
    @NotBlank
    @Length(min = 3, max = 255)
    private String description;
    private String hotelType;
    @Valid
    private AddressForm address;
}
