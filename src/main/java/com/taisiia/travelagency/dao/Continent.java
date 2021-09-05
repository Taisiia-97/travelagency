package com.taisiia.travelagency.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "continents")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "continent_name")
    private String continentName;

}
