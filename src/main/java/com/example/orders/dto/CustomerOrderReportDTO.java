package com.example.orders.dto;

import lombok.Getter;
import java.time.YearMonth;

@Getter
public class CustomerOrderReportDTO {
    private String customerName;
    private YearMonth yearMonth;
    private long orderCount;
    private long productCount;

    public CustomerOrderReportDTO(String customerName, YearMonth yearMonth, long orderCount, long productCount) {
        this.customerName = customerName;
        this.yearMonth = yearMonth;
        this.orderCount = orderCount;
        this.productCount = productCount;
    }

}
