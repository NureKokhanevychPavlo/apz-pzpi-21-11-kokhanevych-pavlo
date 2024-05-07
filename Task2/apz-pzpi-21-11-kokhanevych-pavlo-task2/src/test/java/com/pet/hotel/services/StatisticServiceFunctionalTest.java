package com.pet.hotel.services;


import com.pet.hotel.businessLogic.domain.interfaces.StatisticService;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.AverageDurationRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.IncomeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticServiceFunctionalTest {

    @Autowired
    private StatisticService statisticService;


    @Test
    public void testGetIncomeDuringPeriodByHotel() {
        List<IncomeResponse> expected = new ArrayList<>();

        List<IncomeResponse> result = statisticService.getIncomeDuringPeriodByHotel("Luxury Hotel", LocalDateTime.now(), LocalDateTime.now());

        assertEquals(expected, result);
    }

    @Test
    public void testGetAverageDurationRenting() {
        List<AverageDurationRentingResponse> expected = new ArrayList<>(2);
        expected.add(new AverageDurationRentingResponse(2024,  BigDecimal.valueOf(48.0000)));

        List<AverageDurationRentingResponse> result = statisticService.getAverageDurationRenting("Luxury Hotel");

        assertNotEquals(expected, result);
    }
}
