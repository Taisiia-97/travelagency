package com.taisiia.travelagency.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)

    UserDto toDto(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "isActive",ignore = true)
    User toDao(UserDto userDto);
}
