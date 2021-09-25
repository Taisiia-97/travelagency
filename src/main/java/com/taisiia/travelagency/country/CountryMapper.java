package com.taisiia.travelagency.country;

import com.taisiia.travelagency.country.Country;
import com.taisiia.travelagency.country.CountryDto;
import com.taisiia.travelagency.country.CountryForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);

    @Mapping(target = "continents", ignore = true)
    Country toDao(CountryForm countryForm);

    List<CountryDto> toListDto(List<Country> countries);
}
