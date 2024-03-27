package com.ecommerce.paymentService.module;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table (name = "payments")

public class Payments {

    @Id
    private Integer paymentId;
    private Long orderId;
    private LocalDateTime paymentDate;
    private Double amount;
    private String paymentMethod;
    private String status;
}
