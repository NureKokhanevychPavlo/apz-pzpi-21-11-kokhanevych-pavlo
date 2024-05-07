package com.pet.hotel.repositories;

import com.pet.hotel.data.database.repositories.RentEntity;
import com.pet.hotel.data.models.ScheduleOfFeed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RentDtoRepositoryFunctionalTest {
    @Autowired
    private RentEntity rentRepository;

    @Test
    public void testGetScheduleOfFeedById() {
        int rentId = 11;

        Optional<List<ScheduleOfFeed>> scheduleList = rentRepository.getScheduleOfFeedById(rentId);

        assertTrue(scheduleList.isPresent());
        assertFalse(scheduleList.get().isEmpty());
    }

    @Test
    public void testGetRentingByDateAndRoom() {
        // Arrange
        int roomId = 4;
        LocalDateTime dateTime = LocalDateTime.now();

        // Act
        com.pet.hotel.data.database.entities.RentEntity result = rentRepository.findRentByDateAndRoom(roomId, dateTime).get();

        // Assert
        assertNotNull(result);
    }


    @Test
    public void testGetScheduleOfFeedById_EmptyResult() {
        int rentId = 12;

        Optional<List<ScheduleOfFeed>> scheduleList = rentRepository.getScheduleOfFeedById(rentId);

        assertTrue(scheduleList.isPresent());
    }
}
