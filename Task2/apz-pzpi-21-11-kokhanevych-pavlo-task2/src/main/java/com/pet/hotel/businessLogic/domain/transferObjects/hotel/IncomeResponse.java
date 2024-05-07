package com.pet.hotel.businessLogic.domain.transferObjects.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class IncomeResponse {

    @JsonProperty("rental_date")
    private LocalDate rentalDate;

    @JsonProperty("total_income")
    private int totalIncome;
}
