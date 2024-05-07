package com.pet.hotel.businessLogic.domain.transferObjects.authentication;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;
}
