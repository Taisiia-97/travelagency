package com.taisiia.travelagency.continent;

import com.taisiia.travelagency.continent.Continent;
import com.taisiia.travelagency.continent.ContinentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContinentMapper {

    ContinentDto toDto(Continent continent);

    Continent toDao(ContinentDto continentDto);


    List<ContinentDto> toListDto(List<Continent> continents);
}
