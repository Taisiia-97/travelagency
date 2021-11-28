package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dao.Country;
import com.taisiia.travelagency.domain.dto.CountryDto;
import com.taisiia.travelagency.domain.form.CountryForm;
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
