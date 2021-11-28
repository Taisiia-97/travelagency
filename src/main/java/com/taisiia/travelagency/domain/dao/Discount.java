package com.taisiia.travelagency.domain.dao;

import com.taisiia.travelagency.enums.DiscountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name;
    private Double percent;
    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
    @Enumerated(value = EnumType.STRING)
    private DiscountStatus status;





}
