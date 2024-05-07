package com.pet.hotel.businessLogic.services;


import com.pet.hotel.businessLogic.domain.interfaces.StripeService;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.stripe.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.api.publicKey}")
    private String publicKey;

    void init() {
        Stripe.apiKey = publicKey;
    }

    @Override
    @Async
    public StripeResponse createPaymentIntent(StripeRequest request) {

        try {
            PaymentIntentCreateParams params = getPaymentIntentCreateParams(request);
            PaymentIntent intent = PaymentIntent.create(params);

            return new StripeResponse(intent.getId(),
                    intent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PaymentIntentCreateParams getPaymentIntentCreateParams(StripeRequest request) {
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());

        return PaymentIntentCreateParams.builder()
                .setAmount(request.getAmount())
                .setCurrency("usd")
                .setCustomer(customer.getId())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams
                                .AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();
    }
}
