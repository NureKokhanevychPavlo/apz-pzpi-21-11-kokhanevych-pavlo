package com.pet.hotel.controllers;


import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void updateUserAccountTest() {
        ResponseEntity<Void> response = restTemplate.postForEntity("/user/account/save",null, Void.class, "test@example.com");

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getPresentUserReturnsForbidden() {
        ResponseEntity<UserDto> response = restTemplate.getForEntity("/user/account/{email}", UserDto.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getUserPhotoReturnsForbidden() {
        ResponseEntity<byte[]> response = restTemplate.getForEntity("/user/account/{email}", byte[].class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void deleteUserTest() {
        ResponseEntity<Void> response = restTemplate.postForEntity("/user/account/{userId}/delete", null, Void.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getPetsOfUser() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/{userId}/listOfPets", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void addPetsOfUser() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/addPets", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getHistoryRenting() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/{userId}/historyRenting", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    public void getAllHotels() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/hotels", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getRoomsByHotel() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/hotels/{hotelId}/rooms", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void addNewRentingForUser() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/addNewRenting", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getAllFreeRoomByPeriod() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/hotels/{hotelId}/freeRooms", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void sendMessageToAdmin() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/{userId}/feedback", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void autogenerateSchedule() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/rent/autogenerateSchedule", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getScheduleFeed() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/rent/schedule", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void updateTimeOfScheduleFeed() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/rent/updateSchedule", String.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
