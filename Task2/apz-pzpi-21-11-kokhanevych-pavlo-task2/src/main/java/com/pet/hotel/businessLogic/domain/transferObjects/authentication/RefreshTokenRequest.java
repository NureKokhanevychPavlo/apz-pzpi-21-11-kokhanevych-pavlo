package com.pet.hotel.businessLogic.domain.transferObjects.authentication;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
