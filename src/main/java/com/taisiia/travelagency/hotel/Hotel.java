package com.taisiia.travelagency.hotel;

import com.taisiia.travelagency.address.Address;
import com.taisiia.travelagency.hotel.HotelType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
