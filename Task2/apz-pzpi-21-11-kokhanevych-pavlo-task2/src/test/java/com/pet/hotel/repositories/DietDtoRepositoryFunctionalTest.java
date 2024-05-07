package com.pet.hotel.repositories;

import com.pet.hotel.data.database.entities.DietEntity;
import com.pet.hotel.data.database.repositories.DietRepository;
import com.pet.hotel.data.enums.FoodType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DietDtoRepositoryFunctionalTest {

    @Autowired
    private DietRepository dietRepository;

    @Test
    public void testSaveAndFindAll() {
        // Given
        DietEntity dietEntity = new DietEntity();
        dietEntity.setPortion(0.5f);
        dietEntity.setTypeFood(FoodType.valueOf("WATER"));;

        // When
        dietRepository.findAll();

        // Then
        List<DietEntity> diets = dietRepository.findAll();
        assertNotNull(diets);
        assertEquals(2, diets.size());
    }
}
