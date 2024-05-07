package com.pet.hotel.businessLogic.domain.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.data.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietDto {

    @JsonProperty("diet_id")
    private int dietId;

    @JsonProperty("portion")
    private float portion;

    @JsonProperty("type_food")
    private FoodType typeFood;

    @JsonProperty("rent")
    private RentDto rentDto;

    public DietDto(float portion, FoodType typeFood, RentDto rentDto) {
        this.portion = portion;
        this.typeFood = typeFood;
        this.rentDto = rentDto;
    }
}
