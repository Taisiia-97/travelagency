package com.taisiia.travelagency.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_number")
    private Integer streetNumber;
    @Column(name = "local_number")
    private Integer localNumber;
}
