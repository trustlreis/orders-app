package com.example.orders.service;

import com.example.orders.dto.CustomerOrderReportDTO;
import com.example.orders.model.Order;
import com.example.orders.model.OrderDetail;
import com.example.orders.repository.OrderDetailRepository;
import com.example.orders.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public int save(Order order) {
        int result = orderRepository.save(order);
        for (OrderDetail detail : order.getOrderDetails()) {
            orderDetailRepository.save(detail);
        }
        return result;
    }

    public List<CustomerOrderReportDTO> getCustomerOrderReport() {
        return orderRepository.getCustomerOrderReport();
    }

}
