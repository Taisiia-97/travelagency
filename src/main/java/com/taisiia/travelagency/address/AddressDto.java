package com.taisiia.travelagency.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.city.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;
    private CityDto city;
    private String streetName;
    private Integer streetNumber;
    private Integer localNumber;
}
