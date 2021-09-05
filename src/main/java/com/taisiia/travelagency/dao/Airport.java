package com.taisiia.travelagency.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "airports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;


}
