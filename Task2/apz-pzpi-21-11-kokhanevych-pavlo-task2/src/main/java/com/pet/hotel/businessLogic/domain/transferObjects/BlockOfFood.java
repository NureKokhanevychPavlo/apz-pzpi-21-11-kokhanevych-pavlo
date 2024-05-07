package com.pet.hotel.businessLogic.domain.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.hotel.data.enums.FoodType;

public class BlockOfFood {

    @JsonProperty("food_type")
    private FoodType foodType;

    private float portion;

    @JsonProperty("level_filling")
    private int levelOfFilling;
}
