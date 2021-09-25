package com.taisiia.travelagency.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.continent.ContinentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {
    private Long id;
    private String countryName;
    private Set<ContinentDto> continents;

}
