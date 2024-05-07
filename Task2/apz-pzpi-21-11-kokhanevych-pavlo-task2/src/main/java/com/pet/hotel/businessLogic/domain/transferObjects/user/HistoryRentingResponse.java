package com.pet.hotel.businessLogic.domain.transferObjects.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRentingResponse {

    @JsonProperty("begin_date")
    private LocalDateTime beginDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("pet_name")
    private String petName;

    @JsonProperty("hotel_number")
    private int numberRoom;

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("total_payment")
    private int totalPayment;
}
