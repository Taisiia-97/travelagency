package com.taisiia.travelagency.controller;

import com.taisiia.travelagency.domain.dto.OrderDto;
import com.taisiia.travelagency.domain.form.OrderForm;
import com.taisiia.travelagency.mapper.OrderMapper;
import com.taisiia.travelagency.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tour_order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('user:order')")
    public OrderDto createOrder(@RequestBody @Valid OrderForm orderForm) {
        return orderMapper.toDto(orderService.saveOrder(orderMapper.toDao(orderForm), orderForm.getTourUuid(),
                orderForm.getDiscountCode()));
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('user:order')")
    public List<OrderDto> findUserOrder() {
        return orderMapper.toListDto(orderService.findUserOrders(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:write')")
    public List<OrderDto> findAllOrder() {
        return orderMapper.toListDto(orderService.findAll());
    }

    @GetMapping("/date")
    @PreAuthorize("hasAuthority('user:write')")
    public List<OrderDto> findOrdersByDate(@RequestParam String date) {
        return orderMapper.toListDto(orderService.findOrdersByDate(date));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public OrderDto findOrderById(@PathVariable Long id) {
        return orderMapper.toDto(orderService.findById(id));
    }

    @PutMapping("/payments/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void changePaymentStatusById(@PathVariable Long id) {
        orderService.changePayStatus(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteById(id);
    }

}
