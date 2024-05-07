package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface DeviceService {

    StateOfRoomResponse getStateOfRoom(int roomId);

    boolean setNewStateOfRoom(int roomId, StateOfRoomResponse stateOfRoomResponse);

    boolean updateStateOfRoom(StateOfRoomRequest stateOfRoomRequest, int roomId);

    public boolean sendEmailToUser(String message, int userId);

    RentDto getRentingByDateAndRoom(LocalDateTime dateTime, int roomId);

    RoomDto getInfoRoomByIP(String ip);

    Mono<ServerResponse> startVideoStream(int roomId);

    Mono<ServerResponse> stopVideoStream(int roomId);
}
