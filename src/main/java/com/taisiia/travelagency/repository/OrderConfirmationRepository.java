package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.OrderConfirmation;
import com.taisiia.travelagency.domain.dao.TourOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface OrderConfirmationRepository extends JpaRepository<OrderConfirmation, Long> {

    OrderConfirmation  findByOrder(TourOrder tourOrder);

    void deleteByOrderId(Long id);
}
