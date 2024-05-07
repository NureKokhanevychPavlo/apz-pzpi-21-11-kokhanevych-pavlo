package com.pet.hotel.data.models;

import java.time.LocalDateTime;

public interface HistoryOfRenting {

    LocalDateTime getBeginDate();

    LocalDateTime getEndDate();

    String getPetName();

    int getNumberRoom();

    String getHotelName();

    int getTotalPayment();
}
