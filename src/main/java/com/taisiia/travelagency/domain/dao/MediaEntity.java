package com.taisiia.travelagency.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ordinalFileName;
    private String uuid;
    private String fileName;
    private String extension;


    public MediaEntity(String ordinalFileName, String extension) {
        this.uuid = UUID.randomUUID().toString();
        this.ordinalFileName = ordinalFileName;
        this.extension = extension;
        this.fileName = uuid + extension;
    }


}
