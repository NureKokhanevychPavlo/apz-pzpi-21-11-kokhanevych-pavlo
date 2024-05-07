package com.pet.hotel.presentation.controllers;

import com.pet.hotel.businessLogic.domain.interfaces.AdminService;
import com.pet.hotel.businessLogic.domain.interfaces.DatabaseService;
import com.pet.hotel.businessLogic.domain.transferObjects.*;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/databaseAdmin")
public class DatabaseAdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/restore")
    @Async
    public ResponseEntity<Void> restoreDatabase(byte[] backupPathHash) {
        if (databaseService.restoreDatabase(backupPathHash)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/backup")
    @Async
    public ResponseEntity<Void> backupDatabase(byte[] backupPathHash) {
        if (databaseService.doBackupDatabase(backupPathHash)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allUsers")
    @Async
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/allPets")
    @Async
    public ResponseEntity<List<PetDto>> getAllPets() {
        return ResponseEntity.ok(adminService.getAllPets());
    }

    @GetMapping("/allHotel")
    @Async
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(adminService.getAllHotel());
    }

    @GetMapping("/allRoom")
    @Async
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(adminService.getAllRoom());
    }

    @GetMapping("/allRent")
    @Async
    public ResponseEntity<List<RentDto>> getAllRents() {
        return ResponseEntity.ok(adminService.getAllRent());
    }

    @GetMapping("/allDiet")
    @Async
    public ResponseEntity<List<DietDto>> getAllDiets() {
        return ResponseEntity.ok(adminService.getAllDiet());
    }

    @GetMapping("/allSchedule")
    @Async
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        return ResponseEntity.ok(adminService.getAllSchedule());
    }

    @PostMapping("/addUsers")
    @Async
    public  ResponseEntity<Void> addUsers(@RequestBody List<UserDto> users) {
        if (adminService.addObject(users)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addPets")
    @Async
    public  ResponseEntity<Void> addPets(@RequestBody List<PetDto> petDtos) {
        if (adminService.addObject(petDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addHotels")
    @Async
    public  ResponseEntity<Void> addHotels(@RequestBody List<HotelDto> hotelDtos) {
        if (adminService.addObject(hotelDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addRooms")
    @Async
    public  ResponseEntity<Void> addRooms(@RequestBody List<RoomDto> roomDtos) {
        if (adminService.addObject(roomDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addRent")
    @Async
    public  ResponseEntity<Void> addRents(@RequestBody List<RentDto> rentDtos) {
        if (adminService.addObject(rentDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addDiet")
    @Async
    public  ResponseEntity<Void> addDiets(@RequestBody List<DietDto> dietDtos) {
        if (adminService.addObject(dietDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addSchedule")
    @Async
    public  ResponseEntity<Void> addSchedules(@RequestBody List<ScheduleDto> scheduleDtos) {
        if (adminService.addObject(scheduleDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/deleteUsers")
    @Async
    public  ResponseEntity<Void> deleteUsers(@RequestBody List<UserDto> users) {
        if (adminService.deleteObjects(users)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deletePets")
    @Async
    public  ResponseEntity<Void> deletePets(@RequestBody List<PetDto> petDtos) {
        if (adminService.deleteObjects(petDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteHotels")
    @Async
    public  ResponseEntity<Void> deleteHotels(@RequestBody List<HotelDto> hotelDtos) {
        if (adminService.deleteObjects(hotelDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteRooms")
    @Async
    public  ResponseEntity<Void> deleteRooms(@RequestBody List<RoomDto> roomDtos) {
        if (adminService.deleteObjects(roomDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteRent")
    @Async
    public  ResponseEntity<Boolean> deleteRents(@RequestBody List<RentDto> rentDtos) {
        if (adminService.deleteObjects(rentDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteDiet")
    @Async
    public  ResponseEntity<Void> deleteDiets(@RequestBody List<DietDto> dietDtos) {
        if (adminService.deleteObjects(dietDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deleteSchedule")
    @Async
    public  ResponseEntity<Void> deleteSchedules(@RequestBody List<ScheduleDto> scheduleDtos) {
        if (adminService.deleteObjects(scheduleDtos)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
