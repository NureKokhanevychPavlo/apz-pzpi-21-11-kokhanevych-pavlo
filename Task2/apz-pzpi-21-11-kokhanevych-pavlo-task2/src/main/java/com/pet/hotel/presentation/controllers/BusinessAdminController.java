package com.pet.hotel.presentation.controllers;

import com.pet.hotel.businessLogic.domain.interfaces.AdminService;
import com.pet.hotel.businessLogic.domain.interfaces.StatisticService;
import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.AverageDurationRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.IncomeResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/businessAdmin")
public class BusinessAdminController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/durationRenting")
    @Async
    public ResponseEntity<List<AverageDurationRentingResponse>> getAverageDurationRenting(String nameHotel) {
        return ResponseEntity.ok(statisticService.getAverageDurationRenting(nameHotel));
    }

    @GetMapping("/incomePeriod")
    @Async
    public ResponseEntity<List<IncomeResponse>> getIncomeDuringPeriodByHotel(String nameHotel, LocalDateTime beginDate, LocalDateTime endDate) {
        return ResponseEntity.ok(statisticService.getIncomeDuringPeriodByHotel(nameHotel, beginDate, endDate));
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
}
