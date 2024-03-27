package com.ecommerce.paymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class PaymentServiceSpringBootApp
{
    public static void main( String[] args )
    {
        System.out.println( "Payment Service Started" );
        SpringApplication.run(PaymentServiceSpringBootApp.class);
    }
}
