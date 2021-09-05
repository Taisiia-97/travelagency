package com.taisiia.travelagency.dto;

import com.taisiia.travelagency.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.PrimitiveIterator;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
private String firstName;
private String lastName;
private String email;
private String password;
private Role role;
private Set<TourOrderDto> orders;
}
