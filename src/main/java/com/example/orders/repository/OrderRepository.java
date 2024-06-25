package com.example.orders.repository;

import com.example.orders.dto.CustomerOrderReportDTO;
import com.example.orders.model.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
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

    public List<CustomerOrderReportDTO> getCustomerOrderReport() {
        String sql = """
                SELECT c.name AS customer_name,
                       DATE_TRUNC('month', o.order_date) AS year_month,
                       COUNT(DISTINCT o.id) AS order_count,
                       COUNT(od.id) AS product_count
                FROM customer c
                JOIN orders o ON c.id = o.customer_id
                JOIN order_detail od ON o.id = od.order_id
                GROUP BY c.name, DATE_TRUNC('month', o.order_date)
                ORDER BY c.name, year_month
                """;

        return jdbcTemplate.query(sql, new CustomerOrderReportRowMapper());
    }

    private static class CustomerOrderReportRowMapper implements RowMapper<CustomerOrderReportDTO> {
        @Override
        public CustomerOrderReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            String customerName = rs.getString("customer_name");
            YearMonth yearMonth = YearMonth.of(rs.getDate("year_month").toLocalDate().getYear(),
                                               rs.getDate("year_month").toLocalDate().getMonth());
            long orderCount = rs.getLong("order_count");
            long productCount = rs.getLong("product_count");
            return new CustomerOrderReportDTO(customerName, yearMonth, orderCount, productCount);
        }
    }
}
