package com.taisiia.travelagency.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourSearchForm {
    private String departureCity;
    private String hotelPurposeCity;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnDate;
    private String hotelType;
    private String hotelStandard;
    private Double minPrice;
    private Double maxPrice;


}
