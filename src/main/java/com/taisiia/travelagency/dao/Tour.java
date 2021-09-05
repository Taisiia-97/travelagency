package com.taisiia.travelagency.dao;

import com.taisiia.travelagency.enums.TourStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "id")
    private Airport fromAirPort;
    @ManyToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "id")
    private Airport purposeAirport;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "end_date")
    private Date returnDate;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;
    @Column(name = "adult_price")
    private Double adultPrice;
    @Column(name = "child_price")
    private Double childPrice;
    @ManyToMany
    @JoinTable(name = "tour_promotion", inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    List<Promotion> promotions = new ArrayList<>();
    @Column(name = "number_place_adults")
    private Integer placesAdults;
    @Column(name = "number_place_child")
    private Integer placesChild;
    private String photo;
    @Enumerated(value = EnumType.STRING)
    private TourStatus status;

}
