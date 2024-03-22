package com.ecommerce.orderService.payload;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private long orderId;
    private long userId;
    private LocalDateTime orderDate;
    private List<Long> productIds;
    private double totalAmount;
    private String status;
}
