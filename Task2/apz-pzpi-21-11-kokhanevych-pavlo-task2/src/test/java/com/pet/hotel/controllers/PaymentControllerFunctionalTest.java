package com.pet.hotel.controllers;


import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerFunctionalTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreatePaymentToken() {
        // Arrange
        StripeRequest stripeRequest = new StripeRequest();
        stripeRequest.setEmail("pasakane990@gmail.com");
        stripeRequest.setAmount(400L);

        // Act
        ResponseEntity<StripeResponse> responseEntity = restTemplate.postForEntity(
                "/payment/buy/card", stripeRequest, StripeResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
