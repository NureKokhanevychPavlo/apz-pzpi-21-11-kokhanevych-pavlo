package com.pet.hotel.security.configuration;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @PostConstruct
    public void  initSecretKey(){
        Stripe.apiKey = secretKey;
    }
}
