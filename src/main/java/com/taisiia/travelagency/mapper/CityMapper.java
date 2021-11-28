package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dao.City;
import com.taisiia.travelagency.domain.dto.CityDto;
import com.taisiia.travelagency.domain.form.CityForm;
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
