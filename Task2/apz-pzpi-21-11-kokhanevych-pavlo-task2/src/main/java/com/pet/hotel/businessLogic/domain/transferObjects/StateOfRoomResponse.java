package com.pet.hotel.businessLogic.domain.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateOfRoomResponse {

    private float temperature;

    private float humidity;

    private int brightness;

    @JsonProperty("blocks_food")
    private List<BlockOfFood> blocksOfFood;
}
