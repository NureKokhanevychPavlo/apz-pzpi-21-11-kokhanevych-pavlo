package com.pet.hotel.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessAdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAverageDurationRenting() {
        String nameHotel = "Luxury Hotel";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/businessAdmin/durationRenting", String.class, nameHotel);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetIncomeDuringPeriodByHotel() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/businessAdmin/incomePeriod", String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllUsersReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allUsers",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllPetsReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allPets",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllHotelsReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allHotel",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllRoomReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allRoom",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllRentReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allRent",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllDietReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allDiet",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllScheduleReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allSchedule",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
}