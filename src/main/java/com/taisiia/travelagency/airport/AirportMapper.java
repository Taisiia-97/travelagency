package com.taisiia.travelagency.airport;

import com.taisiia.travelagency.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",uses = AddressMapper.class,imports = {UUID.class})
public interface AirportMapper {

    AirportDto toDto(Airport airport);

    @Mapping(source = "addressForm",target = "address")
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID().toString())")
    Airport toDao(AirportForm airportForm);

    List<AirportDto> toListDto(List<Airport> airports);
}
