package com.pet.hotel.repositories;


import com.pet.hotel.data.database.entities.ScheduleEntity;
import com.pet.hotel.data.database.repositories.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScheduleDtoRepositoryFunctionalTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void testGetAllSchedules() {
        List<ScheduleEntity> schedules = scheduleRepository.findAll();

        assertNotNull(schedules);
        assertFalse(schedules.isEmpty());
    }
}
