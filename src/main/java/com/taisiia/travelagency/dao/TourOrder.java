package com.taisiia.travelagency.dao;

import com.taisiia.travelagency.tour.Tour;
import com.taisiia.travelagency.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TourOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_user",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
    private Double totalPrice;

}
