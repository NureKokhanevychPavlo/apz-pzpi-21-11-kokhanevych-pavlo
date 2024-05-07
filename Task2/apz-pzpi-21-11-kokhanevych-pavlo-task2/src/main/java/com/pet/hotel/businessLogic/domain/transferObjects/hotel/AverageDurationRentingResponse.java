package com.pet.hotel.businessLogic.domain.transferObjects.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AverageDurationRentingResponse {

    @JsonProperty("year")
    private int year;

    @JsonProperty("average_rent_duration")
    private BigDecimal averageRentDuration;

    AverageDurationRentingResponse() {}
}
