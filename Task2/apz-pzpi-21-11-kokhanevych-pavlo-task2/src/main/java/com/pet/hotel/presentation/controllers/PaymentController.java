package com.pet.hotel.presentation.controllers;

import com.pet.hotel.businessLogic.domain.interfaces.StripeService;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeServiceImpl;

    @PostMapping("/buy/card")
    @Async
    public ResponseEntity<StripeResponse> createPaymentIntent(@RequestBody StripeRequest request) {
        return ResponseEntity.ok(stripeServiceImpl.createPaymentIntent(request));
    }
}
