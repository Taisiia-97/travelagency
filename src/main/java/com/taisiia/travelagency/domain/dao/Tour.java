package com.taisiia.travelagency.domain.dao;

import com.taisiia.travelagency.enums.TourStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Double adultRegularPrice;
    @Column(name = "child_price")
    private Double childPrice;
    private Double adultDiscountPrice;
    private Double adultDiscountPercent;
    private boolean isActiveDiscount;
    @Column(name = "number_place_adults")
    private Integer placesAdults;
    @Column(name = "number_place_child")
    private Integer placesChild;
    @Enumerated(value = EnumType.STRING)
    private TourStatus status;

}
