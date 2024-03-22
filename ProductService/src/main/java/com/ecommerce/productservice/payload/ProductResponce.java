package com.ecommerce.productservice.payload;

import com.ecommerce.productservice.model.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductResponce {

        private int productId;
        private String productName;
        private String description;
        private double price;

//
//        public static ProductResponce fromProduct(Product product) {
//                return ProductResponce.builder()
//                        .productId(product.getProductId())
//                        .productName(product.getProductName())
//                        .description(product.getDescription())
//                        .price(product.getPrice())
//                        .build();
//        }
}


