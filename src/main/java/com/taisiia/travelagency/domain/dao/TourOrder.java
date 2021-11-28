package com.taisiia.travelagency.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TourOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    private User user;
    private Integer adultsPlaces;
    private Integer childPlaces;
    private BigDecimal totalPriceWithoutDiscount;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private Boolean isPaid;
}
