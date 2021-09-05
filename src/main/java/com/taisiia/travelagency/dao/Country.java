package com.taisiia.travelagency.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_code")
    private String countryCode;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "country_continent", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
    private Set<Continent> continents = new HashSet<>();

}
