package com.taisiia.travelagency.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.enums.DiscountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountDto {
    private Long id;
    private String name;
    private String code;
    private Double percent;
    private DiscountStatus status;
    private LocalDateTime endDate;
}
