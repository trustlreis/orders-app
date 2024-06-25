package com.example.orders.repository;

import com.example.orders.model.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * FROM orders", new BeanPropertyRowMapper<>(Order.class));
    }

    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO orders (customer_id, order_date) VALUES (?, ?)",
                                   order.getCustomerId(), order.getOrderDate());
    }
}
