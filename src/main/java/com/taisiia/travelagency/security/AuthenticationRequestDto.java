package com.taisiia.travelagency.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String password;
}
