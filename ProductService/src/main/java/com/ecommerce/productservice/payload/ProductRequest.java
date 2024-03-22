package com.ecommerce.productservice.payload;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {

        private int productId;
        private String productName;
        private String description;
        private double price;

}
