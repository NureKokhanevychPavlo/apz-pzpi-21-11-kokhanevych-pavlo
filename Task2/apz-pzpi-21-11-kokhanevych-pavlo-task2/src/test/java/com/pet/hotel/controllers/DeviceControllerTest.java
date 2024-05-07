package com.pet.hotel.controllers;

import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getStateOfRoomTest() {
        // Arrange
        int userId = 1;

        // Act
        ResponseEntity<StateOfRoomResponse> responseEntity = restTemplate.postForEntity("/device/1/state", null, StateOfRoomResponse.class);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void setNewStateOfRoomTest() {
        // Arrange
        int userId = 1;

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/device/1/setState", null, String.class);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void updateStateOfRoomTest() {
        // Arrange
        int roomId = 1;

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/device/1/updateState", null, String.class);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void startVideoStreamTest() {
        // Arrange
        int roomId = 1;

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/device/1/startStream", null, String.class);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void stopVideoStreamTest() {
        // Arrange
        int roomId = 1;

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/device/1/stopStream", null, String.class);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
}
