package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.domain.interfaces.ScheduleService;
import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.ScheduleFeedResponse;
import com.pet.hotel.businessLogic.mappers.Mapper;
import com.pet.hotel.data.database.entities.ScheduleEntity;
import com.pet.hotel.data.database.repositories.RentEntity;
import com.pet.hotel.data.database.repositories.ScheduleRepository;
import com.pet.hotel.data.enums.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final int MONTH_PER_YEAR = 12;

    private static final float DURATION_OF_FEEDING = 8.0f;

    private static final int BLOCK_CAPACITY = 2;

    private static final int HOUR_PER_DAY = 24;

    private static final float AMOUNT_WATER_FOR_KILOGRAM = 0.08f;

    private static final float AMOUNT_FOOD_FOR_KILOGRAM = 0.1f;

    private static final float MONTH_MIN_TOBE_CHILD = 6;

    private static final float COEFFICIENT_WATER = 1.5f;

    private static final float COEFFICIENT_FOOD = 1.2f;

    @Autowired
    private RentEntity rentRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    @Async
    public List<ScheduleFeedResponse> autogenerateSchedule(RentDto rentDto, int numberOfBlocks) {
        float petWeight = rentDto.getPetDto().getWeight();
        int ageInMonth = rentDto.getPetDto().getAge() * MONTH_PER_YEAR;

        long durationHours = Duration.between(rentDto.getBeginDate(), rentDto.getEndDate()).toHours();

        double waterVolume = calculateWaterVolume(petWeight, ageInMonth, durationHours);
        double foodVolume = calculateFoodWeight(petWeight, ageInMonth, durationHours);

        // Determine the number of blocks for water and feed
        int waterBlocks = (int) Math.ceil(waterVolume / BLOCK_CAPACITY);
        int foodBlocks = (int) Math.ceil(foodVolume / BLOCK_CAPACITY);

        if (waterBlocks + foodBlocks > numberOfBlocks) {
            return new ArrayList<>();
        }

        List<ScheduleFeedResponse> schedule = new ArrayList<>();

        fillSchedule(schedule, waterVolume, FoodType.WATER.toString(), rentDto);
        fillSchedule(schedule, foodBlocks, FoodType.SAVORY.toString(), rentDto);

        return schedule;
    }

    private void fillSchedule(List<ScheduleFeedResponse> scheduleFeedResponse, double volume, String foodType, RentDto rentDto) {
        long durationHours = Duration.between(rentDto.getBeginDate(), rentDto.getEndDate()).toHours();
        int numberOfFeedings = (int) Math.ceil(durationHours / DURATION_OF_FEEDING);

        double portionOfFood = volume / numberOfFeedings;

        LocalDateTime timeOfEating = rentDto.getBeginDate();

        for (int i = 0; i < numberOfFeedings; i++) {
            scheduleFeedResponse.add(new ScheduleFeedResponse(0, timeOfEating, (float) portionOfFood, FoodType.valueOf(foodType)));

            timeOfEating = timeOfEating.plusHours((int) DURATION_OF_FEEDING);
        }
    }

    private static double calculateWaterVolume(double weightInKg, int ageInMonths, long hours) {
        double waterPerDay = AMOUNT_WATER_FOR_KILOGRAM * weightInKg;

        // Adjust water requirements based on age (e.g., puppies/kittens need more water)
        if (ageInMonths < MONTH_MIN_TOBE_CHILD) {
            waterPerDay *= COEFFICIENT_WATER; // Puppies/kittens need extra hydration
        }

        return (waterPerDay / HOUR_PER_DAY) * hours;
    }

    private static double calculateFoodWeight(double weightInKg, int ageInMonths, long hours) {
        double foodPerDay = AMOUNT_FOOD_FOR_KILOGRAM * weightInKg;

        // Adjust water requirements based on age (e.g., puppies/kittens need more water)
        if (ageInMonths < MONTH_MIN_TOBE_CHILD) {
            foodPerDay *= COEFFICIENT_FOOD;
        }

        return (foodPerDay / HOUR_PER_DAY) * hours;
    }

    @Override
    @Async
    public List<ScheduleFeedResponse> getScheduleFeed(int rentId) {
        if (rentRepository.existsById(rentId) && rentRepository.getScheduleOfFeedById(rentId).isPresent()) {
            return mapper.map(rentRepository.getScheduleOfFeedById(rentId).get(), ScheduleFeedResponse.class);
        }
        return null;
    }

    @Override
    @Async
    public boolean updateTimeOfScheduleFeed(List<ScheduleFeedResponse> scheduleFeedResponses) {
        if (!scheduleFeedResponses.isEmpty()) {
            List<ScheduleDto> newSchedule = new ArrayList<>();
            for (ScheduleFeedResponse scheduleFeedRespons : scheduleFeedResponses) {
                ScheduleDto scheduleDto = updateDataOfSchedule(scheduleFeedRespons);

                newSchedule.add(scheduleDto);
            }

            scheduleRepository.saveAll(mapper.map(newSchedule, ScheduleEntity.class));
            return true;
        }
        return false;
    }

    private ScheduleDto updateDataOfSchedule(ScheduleFeedResponse scheduleFeedRespons) {
        ScheduleDto scheduleDto = mapper.map(
                scheduleRepository.findById(scheduleFeedRespons.getScheduleId()).get(), ScheduleDto.class
        );

        scheduleDto.setDateTime(scheduleFeedRespons.getDateTime());

        DietDto dietDto = scheduleDto.getDietDto();
        dietDto.setPortion(scheduleFeedRespons.getPortion());
        dietDto.setTypeFood(scheduleFeedRespons.getTypeFood());

        scheduleDto.setDietDto(dietDto);

        return scheduleDto;
    }
}
