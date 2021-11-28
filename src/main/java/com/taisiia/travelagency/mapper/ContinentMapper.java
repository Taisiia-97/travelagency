package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dao.Continent;
import com.taisiia.travelagency.domain.dto.ContinentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContinentMapper {

    ContinentDto toDto(Continent continent);

    Continent toDao(ContinentDto continentDto);


    List<ContinentDto> toListDto(List<Continent> continents);
}
