package com.taisiia.travelagency.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.country.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityDto {
    private Long id;
    private String cityName;
    private CountryDto country;
}
