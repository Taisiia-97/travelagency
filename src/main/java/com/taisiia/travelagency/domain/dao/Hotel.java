package com.taisiia.travelagency.domain.dao;

import com.taisiia.travelagency.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String uuid = UUID.randomUUID().toString();
    @Column(unique = true)
    private String hotelName;
    private Integer standard;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private HotelType hotelType;
    private String photo;
    @OneToOne
    private Address address;

}
