package com.example.orders.controller;

import com.example.orders.dto.CustomerOrderReportDTO;
import com.example.orders.model.Order;
import com.example.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/report")
    public List<CustomerOrderReportDTO> getCustomerOrderReport() {
        return orderService.getCustomerOrderReport();
    }

    @PostMapping
    public int save(@RequestBody Order order) {
        return orderService.save(order);
    }
}
