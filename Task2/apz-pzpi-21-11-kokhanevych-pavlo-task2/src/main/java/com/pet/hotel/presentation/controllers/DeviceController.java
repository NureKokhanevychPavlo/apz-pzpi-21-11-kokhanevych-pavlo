package com.pet.hotel.presentation.controllers;

import com.pet.hotel.businessLogic.domain.interfaces.DeviceService;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{roomId}/state")
    @Async
    public ResponseEntity<StateOfRoomResponse> getStateOfRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(deviceService.getStateOfRoom(roomId));
    }

    @PostMapping("/{roomId}/setState")
    @Async
    public ResponseEntity<Void> setNewStateOfRoom(@PathVariable int roomId, @RequestBody StateOfRoomResponse stateOfRoomResponse) {
        if (deviceService.setNewStateOfRoom(roomId, stateOfRoomResponse)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{roomId}/updateState")
    @Async
    public ResponseEntity<Void> updateStateOfRoom(@PathVariable int roomId, @RequestBody StateOfRoomRequest stateOfRoomRequest) {
        if (deviceService.updateStateOfRoom(stateOfRoomRequest, roomId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{roomId}/startStream")
    @Async
    public ResponseEntity<Mono<ServerResponse>> startVideoStream(@PathVariable int roomId) {
        return ResponseEntity.ok(deviceService.startVideoStream(roomId));
    }

    @GetMapping("/{roomId}/stopStream")
    @Async
    public ResponseEntity<Mono<ServerResponse>> stopVideoStream(@PathVariable int roomId) {
        return ResponseEntity.ok(deviceService.startVideoStream(roomId));
    }

    @PostMapping("/{userId}/sendEmail")
    @Async
    public ResponseEntity<Void> sendEmailToUser(@RequestBody String message, @PathVariable int userId) {
        if (deviceService.sendEmailToUser(message, userId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{roomId}/renting")
    @Async
    public ResponseEntity<RentDto> getRentingByDateAndRoom(@PathVariable int roomId, @RequestBody LocalDateTime dateTime) {
        return ResponseEntity.ok(deviceService.getRentingByDateAndRoom(dateTime, roomId));
    }

    @GetMapping("/room")
    @Async
    public ResponseEntity<RoomDto> getInfoRoomByIP(@RequestBody String ip) {
        return ResponseEntity.ok(deviceService.getInfoRoomByIP(ip));
    }
}
