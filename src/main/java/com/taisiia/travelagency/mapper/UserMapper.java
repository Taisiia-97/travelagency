package com.taisiia.travelagency.mapper;

import com.taisiia.travelagency.dao.User;
import com.taisiia.travelagency.dto.UserDto;
import com.taisiia.travelagency.enums.Role;
import java.util.HashSet;

public class UserMapper {

    public static User mapToUsualUser(UserDto user) {
        return User.builder().firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.USER).orders(new HashSet<>())
                .build();
    }

    public static UserDto mapToDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .orders(TourOrderMapper.map(user.getOrders()))
                .build();
    }


}
