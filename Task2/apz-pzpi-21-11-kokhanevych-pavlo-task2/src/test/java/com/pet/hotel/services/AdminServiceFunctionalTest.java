package com.pet.hotel.services;

import com.pet.hotel.businessLogic.domain.interfaces.AdminService;
import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.data.enums.FoodType;
import com.pet.hotel.data.enums.PetType;
import com.pet.hotel.data.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminServiceFunctionalTest {

    @Autowired
    private AdminService adminService;


    @Test
    public void testGetAllUsers() {
        List<UserDto> expected = new ArrayList<>();

        List<UserDto> result = adminService.getAllUsers();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllPets() {
        List<PetDto> expected = new ArrayList<>();

        List<PetDto> result = adminService.getAllPets();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllHotel() {
        List<HotelDto> expected = new ArrayList<>();

        List<HotelDto> result = adminService.getAllHotel();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllRoom() {
        List<RoomDto> expected = new ArrayList<>();

        List<RoomDto> result = adminService.getAllRoom();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllRent() {
        List<RentDto> expected = new ArrayList<>();

        List<RentDto> result = adminService.getAllRent();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllDiet() {
        List<DietDto> expected = new ArrayList<>();

        List<DietDto> result = adminService.getAllDiet();

        assertNotEquals(expected, result);
    }

    @Test
    public void testGetAllSchedule() {
        List<ScheduleDto> expected = new ArrayList<>();

        List<ScheduleDto> result = adminService.getAllSchedule();

        assertNotEquals(expected, result);
    }

    @Test
    public void testAddUsers() {
        List<UserDto> users = new ArrayList<>();
        UserDto userDto = new UserDto("PAvlo", "Pavlo2@", "fff@gmail.com", LocalDateTime.now(), "ffff", " dsfsf", UserType.CLIENT);
        users.add(userDto);

        assertTrue(adminService.addObject(users));
    }

    @Test
    public void testAddPets() {
        List<PetDto> pets = new ArrayList<>();
        List<UserDto> users = adminService.getAllUsers();

        if (!users.isEmpty()) {
            UserDto userDto = users.get(0);

            PetDto petDto = new PetDto("ff", 44, 3.4f, PetType.CAT, "fff", " fdf", userDto);

            pets.add(petDto);

            assertTrue(adminService.addObject(pets));
        }
    }

    @Test
    public void testAddHotels() {
        List<HotelDto> hotels = new ArrayList<>();
        HotelDto hotelDto = new HotelDto("Test", "ff", "ff", "fff", "fff", "fff");
        hotels.add(hotelDto);

        assertTrue(adminService.addObject(hotels));
    }

    @Test
    public void testAddRooms() {
        List<HotelDto> hotelDto = adminService.getAllHotel();
        List<RoomDto> rooms = new ArrayList<>();
        RoomDto roomDto = new RoomDto(123, 3.4f, 345.5f, "fff", "fff", hotelDto.get(0));
        rooms.add(roomDto);

        assertTrue(adminService.addObject(rooms));
    }
}
