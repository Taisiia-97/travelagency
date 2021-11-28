package com.taisiia.travelagency.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.enums.Role;
import com.taisiia.travelagency.validator.BirthDayValid;
import com.taisiia.travelagency.validator.PasswordValid;
import com.taisiia.travelagency.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordValid
@BirthDayValid
public class UserDto {
    private Long id;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String firstName;
    @NotBlank
    @Length(min = 3, max = 255)
    @Pattern(regexp = "[A-Z]+[a-z]*")
    private String lastName;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDay;
    @NotBlank
    @Length(min = 3, max = 255)
    @Email
    private String email;
    @NotBlank
    @Length(min = 12, max = 20, groups = Create.class)
    private String password;
    private String confirmPassword;
    private Role role;

}
