package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.WebProperties;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String description;
    private double price;

}
