package com.taisiia.travelagency.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
}
