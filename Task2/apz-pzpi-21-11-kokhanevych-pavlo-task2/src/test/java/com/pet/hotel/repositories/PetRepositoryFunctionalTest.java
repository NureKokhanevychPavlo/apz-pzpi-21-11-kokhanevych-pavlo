package com.pet.hotel.repositories;

import com.pet.hotel.data.database.entities.PetEntity;
import com.pet.hotel.data.database.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetRepositoryFunctionalTest {
    @Autowired
    private PetRepository petRepository;

    @Test
    public void testFindAll() {
        List<PetEntity> petList = petRepository.findAll();

        assertNotNull(petList);
    }

    @Test
    public void testFindById() {
        Optional<PetEntity> petOptional = petRepository.findById(1);

        assertTrue(petOptional.isPresent());
    }
}
