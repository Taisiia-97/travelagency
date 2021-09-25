package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.dao.TourOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourOrderRepository extends JpaRepository<TourOrder, Long> {
}
