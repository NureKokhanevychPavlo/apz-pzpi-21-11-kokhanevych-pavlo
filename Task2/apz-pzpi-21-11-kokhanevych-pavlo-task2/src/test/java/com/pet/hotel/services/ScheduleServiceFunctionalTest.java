package com.pet.hotel.services;

import com.pet.hotel.businessLogic.domain.interfaces.ScheduleService;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.ScheduleFeedResponse;
import com.pet.hotel.data.enums.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScheduleServiceFunctionalTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
   public void testAutogenerateScheduleValidData() {
        // Arrange
        RentDto rentDto = new RentDto();

        PetDto petDto = new PetDto("Helen", 4, 2.5f, PetType.CAT, "fff", " fdf", null);

        rentDto.setBeginDate(LocalDateTime.now());
        rentDto.setEndDate(LocalDateTime.now().plusDays(3));
        rentDto.setPetDto(petDto);

        int numberOfBlocks = 4;

        // Act
        List<ScheduleFeedResponse> schedule = scheduleService.autogenerateSchedule(rentDto, numberOfBlocks);

        // Assert
        assertNotNull(schedule);
        assertFalse(schedule.isEmpty());
    }

    @Test
    public void testGetScheduleFeedValidRentId() {
        // Arrange
        int rentId = 11;

        // Act
        List<ScheduleFeedResponse> schedule = scheduleService.getScheduleFeed(rentId);

        // Assert
        assertNotNull(schedule);
    }

    @Test
    public void testGetScheduleFeedInvalidRentId() {
        // Arrange
        int rentId = -1;

        // Act
        List<ScheduleFeedResponse> schedule = scheduleService.getScheduleFeed(rentId);

        // Assert
        assertNull(schedule);
    }

    @Test
    public void testUpdateTimeOfScheduleFeedValidData() {
        // Arrange
        List<ScheduleFeedResponse> scheduleFeedResponses = new ArrayList<>();

        // Act
        boolean result = scheduleService.updateTimeOfScheduleFeed(scheduleFeedResponses);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testUpdateTimeOfScheduleFeedEmptyData() {
        // Arrange
        List<ScheduleFeedResponse> scheduleFeedResponses = new ArrayList<>();

        // Act
        boolean result = scheduleService.updateTimeOfScheduleFeed(scheduleFeedResponses);

        // Assert
        assertFalse(result);
    }
}
