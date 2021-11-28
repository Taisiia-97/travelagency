package com.taisiia.travelagency.pdf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdfDataDto {
    private String userName;
    private String userSurname;
    private String countryNameFrom;
    private String cityNameFrom;
    private String countryNameTo;
    private String cityNameTo;
    private String hotelName;
    private String departureDate;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private BigDecimal price;
}
