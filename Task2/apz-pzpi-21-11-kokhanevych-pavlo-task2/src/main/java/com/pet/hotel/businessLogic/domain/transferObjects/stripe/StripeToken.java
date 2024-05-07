package com.pet.hotel.businessLogic.domain.transferObjects.stripe;

import lombok.Data;

@Data
public class StripeToken {

    private String cardNumber;

    private String expMonth;

    private String expYear;

    private String cvv;

    private String token;

    private String userName;

    private boolean success;
}
