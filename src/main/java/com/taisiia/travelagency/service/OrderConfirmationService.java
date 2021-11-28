package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.OrderConfirmation;
import com.taisiia.travelagency.domain.dao.TourOrder;
import com.taisiia.travelagency.repository.OrderConfirmationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConfirmationService {
    private final OrderConfirmationRepository orderConfirmationRepository;

    public OrderConfirmation saveConfirmation(TourOrder order, String fullPathWithName){
        OrderConfirmation orderConfirmation = new OrderConfirmation(order,fullPathWithName,fullPathWithName.substring(fullPathWithName.lastIndexOf(".")));
        log.info("File was saved in database: " + fullPathWithName);
        return orderConfirmationRepository.save(orderConfirmation);
    }

    @Transactional
    public void deleteByOrderId(Long id) {
        orderConfirmationRepository.deleteByOrderId(id);
    }
}
