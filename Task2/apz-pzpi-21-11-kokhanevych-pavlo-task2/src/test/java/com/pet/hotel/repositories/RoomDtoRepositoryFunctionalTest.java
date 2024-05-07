package com.pet.hotel.repositories;


import com.pet.hotel.data.database.entities.RoomEntity;
import com.pet.hotel.data.database.repositories.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomDtoRepositoryFunctionalTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testGetAllRooms() {
        List<RoomEntity> rooms = roomRepository.findAll();

        assertNotNull(rooms);
        assertFalse(rooms.isEmpty());
    }

    @Test
    public void testGetAllFreeRoomByPeriod() {
        int hotelId = 1;
        LocalDateTime beginDate = LocalDateTime.of(2025, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 4, 30, 23, 59);

        Optional<List<RoomEntity>> roomList = roomRepository.getAllFreeRoomByPeriod(hotelId, beginDate, endDate);

        assertTrue(roomList.isPresent());
    }
}
