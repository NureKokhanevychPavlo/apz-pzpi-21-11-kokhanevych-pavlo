package com.pet.hotel.businessLogic.domain.transferObjects.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {

    private String email;

    private String password;
}
