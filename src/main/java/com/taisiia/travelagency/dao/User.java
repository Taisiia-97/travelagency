package com.taisiia.travelagency.dao;

import com.taisiia.travelagency.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "users")
    private Set<TourOrder> orders = new HashSet<>();
}
