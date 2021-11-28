package com.taisiia.travelagency.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.enums.TourStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDto {
    private Long id;
    private AirportDto fromAirPort;
    private AirportDto purposeAirport;
    private String departureDate;
    private String returnDate;
    private HotelDto hotel;
    private Double adultRegularPrice;
    private Double childPrice;
    private Double adultDiscountPrice;
    private Double adultDiscountPercent;
    private Integer placesAdults;
    private Integer placesChild;
    private boolean isActiveDiscount;
    private TourStatus status;

}
