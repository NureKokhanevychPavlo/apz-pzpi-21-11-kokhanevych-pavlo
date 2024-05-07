package com.pet.hotel.controllers;

import com.pet.hotel.businessLogic.domain.transferObjects.authentication.JwtAuthenticationResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.RefreshTokenRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.authentication.SignInRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.data.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void signInCorrectAuthentication() {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("pasakane990@gmail.com", "avlo1@");

        // Act
        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.postForEntity("/authentication/signIn", signInRequest, JwtAuthenticationResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void refreshCorrectAuthentication() {
        // Arrange
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MTQwNDQwNzQsImV4cCI6MTcxNDY0ODg3NH0.Zj3Ed9aMMexcRItk7Drdu19bDu2gjzsua5fCFzDbvGM");

        // Act
        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.postForEntity("/authentication/refresh", refreshTokenRequest, JwtAuthenticationResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void registerUserCorrectAuthentication() {
        // Arrange
        UserDto userDto = new UserDto("Maks", "Pavl34a%", "pavlo@gmail.com", LocalDateTime.now(), "+380967363423", " dsfsf", UserType.CLIENT);

        // Act
        boolean responseEntity = restTemplate.postForObject("/authentication/register", userDto, Boolean.class);

        // Assert
        assertTrue(responseEntity);
    }
}
