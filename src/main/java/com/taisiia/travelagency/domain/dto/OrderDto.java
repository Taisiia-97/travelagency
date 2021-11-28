package com.taisiia.travelagency.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Long id;
    private TourDto tour;
    private UserDto user;
    private BigDecimal totalPriceWithoutDiscount;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private String orderDate;
}
