package com.pet.hotel.presentation.controllers;


import com.pet.hotel.businessLogic.domain.interfaces.ScheduleService;
import com.pet.hotel.businessLogic.domain.interfaces.UserService;
import com.pet.hotel.businessLogic.domain.transferObjects.*;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.ScheduleFeedResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.user.HistoryRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/account/{email}")
    @Async
    public ResponseEntity<UserDto> getPresentUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/photo/{userId}")
    @Async
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserPhoto(userId));
    }

    @PostMapping("/account/{userId}/delete")
    @Async
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        if (userService.deleteUser(userId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/account/save")
    @Async
    public ResponseEntity<Void> saveUser(@RequestBody UserDto user, @RequestBody MultipartFile photo) {
        if (userService.saveUser(user, photo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/listOfPets")
    @Async
    public ResponseEntity<List<PetDto>> getPetsOfUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getListOfPets(userId));
    }

    @PostMapping("/addPets")
    @Async
    public ResponseEntity<Void> addPetsOfUser(@RequestBody List<PetDto> pets) {
        if (userService.addPets(pets)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/historyRenting")
    @Async
    public ResponseEntity<List<HistoryRentingResponse>> getHistoryRenting(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getHistoryRenting(userId));
    }

    @GetMapping("/hotels")
    @Async
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(userService.getAllHotels());
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    @Async
    public ResponseEntity<List<RoomDto>> getRoomsByHotel(@PathVariable int hotelId) {
        return ResponseEntity.ok(userService.getAllRoomsByHotel(hotelId));
    }

    @PostMapping("/addNewRenting")
    @Async
    public ResponseEntity<Void> addNewRentingForUser(@RequestBody List<ScheduleDto> scheduleDtoList) {
        if (userService.addNewRentingForUser(scheduleDtoList)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hotels/{hotelId}/freeRooms")
    @Async
    public ResponseEntity<List<RoomDto>> getAllFreeRoomByPeriod(@PathVariable int hotelId, LocalDateTime beginDate, LocalDateTime endDate) {
        return ResponseEntity.ok(userService.getAllFreeRoomByPeriod(hotelId, beginDate, endDate));
    }

    @PostMapping("/{userId}/feedback")
    @Async
    public ResponseEntity<Void> sendMessageToAdmin(@PathVariable int userId, @RequestBody String message) {
        if (userService.sendMessageToAdmin(userId, message)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rent/autogenerateSchedule")
    @Async
    public ResponseEntity<List<ScheduleFeedResponse>> autogenerateSchedule(@RequestBody RentDto rentDto, @RequestBody int numberOfBlocks) {
        return ResponseEntity.ok(scheduleService.autogenerateSchedule(rentDto, numberOfBlocks));
    }

    @GetMapping("/rent/schedule")
    @Async
    public ResponseEntity<List<ScheduleFeedResponse>> getScheduleFeed(@RequestBody int rentId) {
        return ResponseEntity.ok(scheduleService.getScheduleFeed(rentId));
    }

    @PostMapping("/rent/updateSchedule")
    @Async
    public ResponseEntity<Void> updateTimeOfScheduleFeed(@RequestBody List<ScheduleFeedResponse> scheduleFeedResponses) {
        if (scheduleService.updateTimeOfScheduleFeed(scheduleFeedResponses)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

