package com.taisiia.travelagency.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.validator.TourDatesValid;
import com.taisiia.travelagency.validator.TourStatusValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TourStatusValid
@TourDatesValid
public class TourForm {
    @NotBlank
    private String fromAirportUuid;
    private String departureAirportUuid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnDate;
    @NotBlank
    private String hotelUuid;
    @Positive
    private Double adultRegularPrice;
    @Positive
    private Double childPrice;
    @PositiveOrZero
    private Double adultDiscountPercent;
    @Positive
    private Integer placesAdults;
    @PositiveOrZero
    private Integer placesChild;
    @NotBlank
    private String status;
}
