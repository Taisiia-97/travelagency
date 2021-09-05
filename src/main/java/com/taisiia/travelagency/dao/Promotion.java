package com.taisiia.travelagency.dao;

import com.taisiia.travelagency.enums.PromotionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Promotion {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "promotion_name")
    private String promotionName;
    private Double percent;
    @Enumerated(value = EnumType.STRING)
    private PromotionStatus status;

}
