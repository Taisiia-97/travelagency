package com.taisiia.travelagency.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = @Index(name = "idx_name", columnList = "name", unique = true))
@Data
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;
    //pozwala na przechowywania więcej niż 255 znaków
    @Lob
    private String body;
}
