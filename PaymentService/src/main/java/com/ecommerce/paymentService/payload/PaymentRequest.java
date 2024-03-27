package com.ecommerce.paymentService.payload;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PaymentRequest {

    private Integer paymentId;
    private Long orderId;
    private LocalDateTime paymentDate;
    private Double amount;
    private String paymentMethod;
    private String status;
}
