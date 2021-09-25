package com.taisiia.travelagency.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taisiia.travelagency.promotion.PromotionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDto {
    private Long id;
    private String uuid;
    private String promotionName;
    private Double percent;
    private PromotionStatus status;
}
