package com.pet.hotel.data.models;

import com.pet.hotel.data.enums.FoodType;

import java.time.LocalDateTime;

public interface ScheduleOfFeed {

    int getScheduleId();

    LocalDateTime getDateTime();

    float getPortion();

    FoodType getTypeFood();
}
