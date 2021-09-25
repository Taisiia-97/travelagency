package com.taisiia.travelagency.airport;

import com.taisiia.travelagency.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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
    private String uuid = UUID.randomUUID().toString();
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


}
