package com.taisiia.travelagency.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    @Mapping(target = "city", ignore = true)
    Address toDao(AddressForm addressForm);

    List<AddressDto> toListDto(List<Address> addresses);
}
