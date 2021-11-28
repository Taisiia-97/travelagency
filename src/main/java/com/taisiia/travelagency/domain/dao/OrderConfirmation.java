package com.taisiia.travelagency.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ordinalFileName;
    private String uuid;
    private String fileName;
    private String extension;
    @OneToOne
    TourOrder order;

    public OrderConfirmation(TourOrder order, String ordinalFileName, String extension) {
        this.uuid = UUID.randomUUID().toString();
        this.ordinalFileName = ordinalFileName;
        this.extension = extension;
        this.fileName = uuid + extension;
        this.order = order;
    }

}
