package com.pet.hotel.repositories;


import com.pet.hotel.data.database.entities.UserEntity;
import com.pet.hotel.data.database.repositories.UserRepository;
import com.pet.hotel.data.models.HistoryOfRenting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDtoRepositoryFunctionalTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetHistoryOfRentingByUserId() {
        int userId = 1;

        Optional<List<HistoryOfRenting>> historyOfRenting = userRepository.getHistoryOfRentingByUserId(userId);

        assertTrue(historyOfRenting.isPresent());
        assertFalse(historyOfRenting.get().isEmpty());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "user1@example.com";

        Optional<UserEntity> user = userRepository.findUserByEmail(email);

        assertTrue(user.isPresent());
    }
}
