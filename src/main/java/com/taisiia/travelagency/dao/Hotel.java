package com.taisiia.travelagency.dao;

import com.taisiia.travelagency.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hotel_name")
    private String hotelName;
    private Integer standard;
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "hotel_type")
    private HotelType hotelType;
    private String photo;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


}
