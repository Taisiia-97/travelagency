package com.taisiia.travelagency.hotel;

import com.taisiia.travelagency.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = AddressMapper.class, imports = {UUID.class})
public interface HotelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "hotelType", ignore = true)
    @Mapping(target = "uuid",expression = "java(UUID.randomUUID().toString())")
    Hotel toDao(HotelForm hotelForm);

    HotelDto toDto(Hotel hotel);

    List<HotelDto> toListDto(List<Hotel> hotels);

}
