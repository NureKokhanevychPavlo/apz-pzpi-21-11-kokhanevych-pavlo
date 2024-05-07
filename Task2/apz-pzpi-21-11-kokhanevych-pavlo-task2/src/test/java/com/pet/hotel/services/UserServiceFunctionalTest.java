package com.pet.hotel.services;

import com.pet.hotel.businessLogic.domain.interfaces.AdminService;
import com.pet.hotel.businessLogic.domain.interfaces.UserService;
import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.HistoryRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.data.enums.FoodType;
import com.pet.hotel.data.enums.PetType;
import com.pet.hotel.data.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceFunctionalTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Test
    void testGetUserByIdExistingUser() {
        // Arrange
        int userId = 1;

        // Act
        UserDto user = userService.getUserById(userId);

        // Assert
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
    }

    @Test
    void testGetUserByIdNonExistingUser() {
        // Arrange
        int userId = 1000;

        // Act
        UserDto user = userService.getUserById(userId);

        // Assert
        assertNull(user);
    }

    @Test
    void testSaveUser() {
        // Arrange
        UserDto userDto = new UserDto("vlo", "RRRR", "den@gmail.com", LocalDateTime.now(), "jjjl", " dsfsf", UserType.CLIENT);
        MultipartFile file = new MockMultipartFile("test2.jpg", "test2.jpg", "image/jpeg", "test file contents".getBytes());

        // Act
        boolean result = userService.saveUser(userDto, file);

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        int userId = 21;

        // Act
        boolean result = userService.deleteUser(userId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testFindUserByEmailExistingEmail() {
        // Arrange
        String email = "user3@example.com";

        // Act
        UserDto user = userService.findUserByEmail(email);

        // Assert
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testFindUserByEmailNonExistingEmail() {
        // Arrange
        String email = "nonexisting@example.com";

        // Act
        UserDto user = userService.findUserByEmail(email);

        // Assert
        assertNull(user);
    }

    @Test
    void testFindUserByEmailNullEmail() {
        // Act
        UserDto user = userService.findUserByEmail(null);

        // Assert
        assertNull(user);
    }

    @Test
    void testFindUserByEmailEmptyEmail() {
        // Act
        UserDto user = userService.findUserByEmail("");

        // Assert
        assertNull(user);
    }

    @Test
    void testGetListOfPetsExistingUserWithPets() {
        // Arrange
        int userId = 1;

        // Act
        List<PetDto> pets = userService.getListOfPets(userId);

        // Assert
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
    }

    @Test
    void testGetListOfPetsExistingUserWithoutPets() {
        // Arrange
        int userId = 3;

        // Act
        List<PetDto> pets = userService.getListOfPets(userId);

        // Assert
        assertNotNull(pets);
        assertTrue(pets.isEmpty());
    }

    @Test
    void testGetListOfPetsNonExistingUser() {
        // Arrange
        int userId = 1000;

        // Act
        List<PetDto> pets = userService.getListOfPets(userId);

        // Assert
        assertNull(pets);
    }

    @Test
    void testAddPetsNonEmptyList() {
        // Arrange
        List<PetDto> pets = new ArrayList<>();
        List<UserDto> users = List.of(userService.getUserById(1));
        pets.add(new PetDto("ff", 3, 2.4f, PetType.CAT, "fff", " fdf", users.get(0)));

        // Act
        boolean result = userService.addPets(pets);

        // Assert
        assertTrue(result);
    }

    @Test
    void testAddPetsEmptyList() {
        // Arrange
        List<PetDto> pets = new ArrayList<>();

        // Act
        boolean result = userService.addPets(pets);

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetHistoryRentingExistingUserWithRentHistory() {
        // Arrange
        int userId = 1;

        // Act
        List<HistoryRentingResponse> history = userService.getHistoryRenting(userId);

        // Assert
        assertNotNull(history);
        assertFalse(history.isEmpty());
    }

    @Test
    void testGetAllHotels() {
        // Act
        List<HotelDto> hotels = userService.getAllHotels();

        // Assert
        assertNotNull(hotels);
        assertFalse(hotels.isEmpty());
    }

    @Test
    void testGetAllRoomsByHotelExistingHotelWithRooms() {
        // Arrange
        int hotelId = 1;

        // Act
        List<RoomDto> rooms = userService.getAllRoomsByHotel(hotelId);

        // Assert
        assertNotNull(rooms);
        assertFalse(rooms.isEmpty());
    }

    @Test
    void testAddNewRentingForUserValidScheduleList() {
        // Arrange
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();
        List<ScheduleDto> scheduleDtos = adminService.getAllSchedule();
        DietDto dietDto = new DietDto(3.2f, FoodType.WATER, scheduleDtos.get(0).getDietDto().getRentDto());
        ScheduleDto scheduleDto = new ScheduleDto(LocalDateTime.now(), dietDto);
        scheduleDtoList.add(scheduleDto);

        // Act
        boolean result = userService.addNewRentingForUser(scheduleDtoList);

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetAllFreeRoomByPeriodValidPeriod() {
        // Arrange
        int hotelId = 1;
        LocalDateTime beginDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);

        // Act
        List<RoomDto> rooms = userService.getAllFreeRoomByPeriod(hotelId, beginDate, endDate);

        // Assert
        assertNotNull(rooms);
        assertFalse(rooms.isEmpty());
    }
}
