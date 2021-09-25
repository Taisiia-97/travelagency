package com.taisiia.travelagency.city;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDto toDto(City city);

    @Mapping(target = "country", ignore = true)
    City toDao(CityForm cityForm);


    List<CityDto> toListDto(List<City> cities);

}
