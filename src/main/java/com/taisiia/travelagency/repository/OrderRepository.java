package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.TourOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<TourOrder, Long> {

    List<TourOrder> findAllByUserEmail(String email);

    List<TourOrder> findAllByOrderDateAfter(LocalDateTime dateTime);
}
