package com.taisiia.travelagency.domain.dao;

import com.taisiia.travelagency.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;
    private Boolean isActive;
}
