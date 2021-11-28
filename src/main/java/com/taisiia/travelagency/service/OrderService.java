package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.OrderConfirmation;
import com.taisiia.travelagency.exception.OrderException;
import com.taisiia.travelagency.mailservice.EmailSenderService;
import com.taisiia.travelagency.domain.dao.TourOrder;
import com.taisiia.travelagency.repository.OrderConfirmationRepository;
import com.taisiia.travelagency.repository.OrderRepository;
import com.taisiia.travelagency.domain.dao.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final TourService tourService;
    private final OrderRepository orderRepository;
    private final EmailSenderService emailSenderService;
    private final OrderConfirmationService orderConfirmationService;

    public TourOrder saveOrder(TourOrder tourOrder, String tourUuid, String discountCode) {
        tourOrder.setUser(userService.findUserByEmailRegistration(SecurityContextHolder.getContext().getAuthentication().getName()));
        Tour tour = tourService.findTourByUuid(tourUuid).orElseThrow(() -> new OrderException("Incorrect tour"));
        tourOrder.setTour(tour);
        BigDecimal totalTourPrice = tourService.calculateTotalTourPrice(tour, tourOrder.getAdultsPlaces(), tourOrder.getChildPlaces());
        tourOrder.setTotalPriceWithoutDiscount(totalTourPrice);
        tourOrder.setTotalDiscount(tourService.calculateDiscount(tour, tourOrder.getAdultsPlaces(), totalTourPrice));
        tourOrder.setTotalPrice(tourOrder.getTotalPriceWithoutDiscount().subtract(tourOrder.getTotalDiscount()));
        if (discountCode != null && !discountCode.isBlank()) {
            tourOrder.setTotalDiscount(tourService.calculateTotalDiscount(tour, discountCode, totalTourPrice));
            tourOrder.setTotalPrice(tourOrder.getTotalPriceWithoutDiscount().subtract(tourOrder.getTotalDiscount()));
        }

        tourOrder.setIsPaid(Boolean.FALSE);
        orderRepository.save(tourOrder);
        emailSenderService.sendOrderConfirmation(tourOrder);
        return tourOrder;
    }


    public List<TourOrder> findUserOrders(String userName) {
        return orderRepository.findAllByUserEmail(userName);
    }

    public List<TourOrder> findAll() {
        return orderRepository.findAll();
    }

    public TourOrder findById(Long id) {
        return orderRepository.getById(id);
    }


    @Transactional
    public void deleteById(Long id) {
        orderConfirmationService.deleteByOrderId(id);
        orderRepository.deleteById(id);
    }

    public List<TourOrder> findOrdersByDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime orderDate = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0);
        return orderRepository.findAllByOrderDateAfter(orderDate);
    }

    @Transactional
    public void changePayStatus(Long id) {
        TourOrder order = findById(id);
        order.setIsPaid(true);
    }
}
