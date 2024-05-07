package com.pet.hotel.businessLogic.domain.transferObjects.stripe;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StripeRequest {

    @NotNull
    @Min(4)
    private Long amount;

    @Email
    private String email;
}
