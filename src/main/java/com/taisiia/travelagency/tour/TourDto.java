package com.taisiia.travelagency.tour;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.taisiia.travelagency.airport.AirportDto;
import com.taisiia.travelagency.hotel.HotelDto;
import com.taisiia.travelagency.promotion.PromotionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Double adultPrice;
    private Double childPrice;
    private Set<PromotionDto> promotions;
    private Integer placesAdults;
    private Integer placesChild;
    private TourStatus status;

}
