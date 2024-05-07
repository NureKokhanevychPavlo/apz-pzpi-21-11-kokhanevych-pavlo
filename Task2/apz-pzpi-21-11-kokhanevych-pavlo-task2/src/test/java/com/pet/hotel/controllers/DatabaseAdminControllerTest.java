package com.pet.hotel.controllers;

import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;;
import com.pet.hotel.data.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatabaseAdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void restoreDatabaseReturnsForbidden() {
        // Arrange
        String backupPathHash = "exampleBackupPathHash";

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/databaseAdmin/restore", null, String.class, backupPathHash);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void backupDatabaseReturnsForbidden() {
        // Arrange
        String backupPathHash = "exampleBackupPathHash";

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/databaseAdmin/backup", null, String.class, backupPathHash);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetIncomeDuringPeriodByHotel() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/databaseAdmin/incomePeriod", String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllUsersReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allUsers",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllPetsReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allPets",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllHotelsReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allHotel",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllRoomReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allRoom",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllRentReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allRent",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllDietReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allDiet",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllScheduleReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/databaseAdmin/allSchedule",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testAddObjects() {

        List<UserDto> users = new ArrayList<>();
        UserDto userDto = new UserDto("PAvlo", "Pavlo2@", "fff@gmail.com", LocalDateTime.now(), "ffff", " dsfsf", UserType.CLIENT);
        users.add(userDto);

        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity("/databaseAdmin/addUsers", users, Boolean.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
