package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.hotel.AverageDurationRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.IncomeResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {

    List<AverageDurationRentingResponse> getAverageDurationRenting(String nameHotel);

    List<IncomeResponse> getIncomeDuringPeriodByHotel(String nameHotel, LocalDateTime beginDate, LocalDateTime endDate);
}
