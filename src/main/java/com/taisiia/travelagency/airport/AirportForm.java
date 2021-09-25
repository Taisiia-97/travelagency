package com.taisiia.travelagency.airport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.address.AddressForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirportForm {
    private Long id;
    @Valid
    private AddressForm addressForm;
}
