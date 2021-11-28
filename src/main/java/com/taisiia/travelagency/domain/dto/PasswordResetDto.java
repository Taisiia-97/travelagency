package com.taisiia.travelagency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetDto {
    @NotBlank
    @Length(min = 12, max = 20)
    private String password;
    @NotBlank
    @Length(min = 12, max = 20)
    private String confirmPassword;
}
