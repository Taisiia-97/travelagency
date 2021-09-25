package com.taisiia.travelagency.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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
    private String uuid = UUID.randomUUID().toString();
    @Column(unique = true)
    private String promotionName;
    private Double percent;
    @Enumerated(value = EnumType.STRING)
    private PromotionStatus status;

}
