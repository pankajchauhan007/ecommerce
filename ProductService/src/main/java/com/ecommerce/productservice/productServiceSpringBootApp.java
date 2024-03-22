package com.ecommerce.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
 class productServiceSpringBootApp {
    public static void main( String[] args )
    {
        System.out.println("Product Service Started");
        SpringApplication.run(productServiceSpringBootApp.class);
    }
}
