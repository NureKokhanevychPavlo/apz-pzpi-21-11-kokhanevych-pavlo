package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.*;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.HistoryRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    UserDto getUserById(Integer userId);

    byte[] getUserPhoto(int userId);

    boolean saveUser(UserDto user, MultipartFile file);

    boolean deleteUser(int userId);

    UserDto findUserByEmail(String email);

    List<PetDto> getListOfPets(int userId);

    boolean addPets(List<PetDto> pets);

    List<HistoryRentingResponse> getHistoryRenting(int userId);

    List<HotelDto> getAllHotels();

    List<RoomDto> getAllRoomsByHotel(int hotelId);

    boolean addNewRentingForUser(List<ScheduleDto> scheduleDtoList);

    List<RoomDto> getAllFreeRoomByPeriod(int hotelId, LocalDateTime beginDate, LocalDateTime endDate);

    boolean sendMessageToAdmin(int userId, String message);

}
