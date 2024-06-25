package com.example.orders.repository;

import com.example.orders.model.OrderDetail;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderDetailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderDetail> findAll() {
        return jdbcTemplate.query("SELECT * FROM order_detail", new BeanPropertyRowMapper<>(OrderDetail.class));
    }

    public int save(OrderDetail orderDetail) {
        return jdbcTemplate.update("INSERT INTO order_detail (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)",
                                   orderDetail.getOrderId(), orderDetail.getProductName(), orderDetail.getQuantity(), orderDetail.getPrice());
    }
}
