package com.ecommerce.paymentService.payload;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PaymentResponse {

        private Integer paymentId;
        private Long orderId;
        private LocalDateTime paymentDate;
        private Double amount;
        private String paymentMethod;
        private String status;
    }

