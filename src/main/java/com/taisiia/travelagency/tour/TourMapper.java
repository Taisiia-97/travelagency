package com.taisiia.travelagency.tour;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface TourMapper {


    @Mapping(target = "uuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "fromAirPort", ignore = true)
    @Mapping(target = "purposeAirport", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "promotions", ignore = true)
    @Mapping(target = "status", ignore = true)
    Tour toDao(TourForm tourForm);

    @Mapping(source = "departureDate", target = "departureDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "returnDate", target = "returnDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TourDto toDto(Tour tour);

    List<TourDto> toListDto(List<Tour> tours);
}
