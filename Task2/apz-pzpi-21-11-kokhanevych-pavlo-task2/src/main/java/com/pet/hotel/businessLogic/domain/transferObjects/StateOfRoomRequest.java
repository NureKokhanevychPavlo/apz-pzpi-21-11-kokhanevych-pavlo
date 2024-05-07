package com.pet.hotel.businessLogic.domain.transferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateOfRoomRequest {

    private float temperature;

    private float humidity;

    private int brightness;
}
