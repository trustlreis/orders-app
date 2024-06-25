package com.example.orders.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long customerId;
    private Date orderDate;
    private List<OrderDetail> orderDetails;
}
