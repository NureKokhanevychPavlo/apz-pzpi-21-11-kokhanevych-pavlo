package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeResponse;

public interface StripeService {

    StripeResponse createPaymentIntent(StripeRequest request);
}
