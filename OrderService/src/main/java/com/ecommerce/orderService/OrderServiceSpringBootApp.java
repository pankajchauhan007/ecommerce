package com.ecommerce.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceSpringBootApp
{
    public static void main( String[] args )
    {
        System.out.println( "Oreder Service Started" );
        SpringApplication.run(OrderServiceSpringBootApp.class);
    }
}
