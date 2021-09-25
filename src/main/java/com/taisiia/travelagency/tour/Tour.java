package com.taisiia.travelagency.tour;

import com.taisiia.travelagency.airport.Airport;
import com.taisiia.travelagency.hotel.Hotel;
import com.taisiia.travelagency.promotion.Promotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tour {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "from_airport_id", referencedColumnName = "id")
    private Airport fromAirPort;
    @ManyToOne
    @JoinColumn(name = "purpose_airport_id", referencedColumnName = "id")
    private Airport purposeAirport;
    @Column(name = "departure_date")
    private LocalDateTime departureDate;
    @Column(name = "end_date")
    private LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;
    @Column(name = "adult_price")
    private Double adultPrice;
    @Column(name = "child_price")
    private Double childPrice;
    @ManyToMany
    @JoinTable(name = "tour_promotion", inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    Set<Promotion> promotions = new HashSet<>();
    @Column(name = "number_place_adults")
    private Integer placesAdults;
    @Column(name = "number_place_child")
    private Integer placesChild;
    @Enumerated(value = EnumType.STRING)
    private TourStatus status;

}
