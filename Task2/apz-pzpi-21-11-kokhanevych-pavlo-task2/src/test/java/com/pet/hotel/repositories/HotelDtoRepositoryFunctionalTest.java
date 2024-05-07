package com.pet.hotel.repositories;

import com.pet.hotel.data.database.repositories.HotelRepository;
import com.pet.hotel.data.models.AverageDurationRenting;
import com.pet.hotel.data.models.Income;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelDtoRepositoryFunctionalTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testGetIncomeDuringPeriodByHotel() {
        String nameHotel = "Luxury Hotel";
        LocalDateTime beginDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 31, 23, 59);

        Optional<List<Income>> incomeList = hotelRepository.getIncomeDuringPeriodByHotel(nameHotel, beginDate, endDate);

        assertTrue(((Optional<?>) incomeList).isPresent());
        assertFalse(incomeList.get().isEmpty());
    }

    @Test
    public void testGetAverageDateOfRenting() {
        String nameHotel = "Luxury Hotel";

        Optional<List<AverageDurationRenting>> averageDateList = hotelRepository.getAverageDateOfRenting(nameHotel);

        assertTrue(averageDateList.isPresent());
        assertFalse(averageDateList.get().isEmpty());
    }
}
