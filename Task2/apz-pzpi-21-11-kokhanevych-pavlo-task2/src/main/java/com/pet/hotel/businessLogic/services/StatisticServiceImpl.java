package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.domain.interfaces.StatisticService;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.AverageDurationRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.IncomeResponse;
import com.pet.hotel.businessLogic.mappers.Mapper;
import com.pet.hotel.data.database.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Async
    public List<AverageDurationRentingResponse> getAverageDurationRenting(String nameHotel) {
        return mapper.map(hotelRepository.getAverageDateOfRenting(nameHotel).orElseGet(ArrayList::new), AverageDurationRentingResponse.class);
    }

    @Override
    @Async
    public List<IncomeResponse> getIncomeDuringPeriodByHotel(String nameHotel, LocalDateTime beginDate, LocalDateTime endDate) {
        return mapper.map(hotelRepository.getIncomeDuringPeriodByHotel(nameHotel, beginDate, endDate)
                .orElseGet(ArrayList::new), IncomeResponse.class);
    }
}
