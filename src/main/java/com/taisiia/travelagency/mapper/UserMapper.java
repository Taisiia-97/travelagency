package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.domain.dao.User;
import com.taisiia.travelagency.domain.dto.UserDto;
import com.taisiia.travelagency.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role",ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    List<UserDto> toListDto(List<User> users);


}
