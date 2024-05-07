package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getAllUsers();

    List<PetDto> getAllPets();

    List<HotelDto> getAllHotel();

    List<RoomDto> getAllRoom();

    List<RentDto> getAllRent();

    List<DietDto> getAllDiet();

    List<ScheduleDto> getAllSchedule();

     <T> boolean addObject(List<T> objectsDto);

     <T> boolean deleteObjects(List<T> objectsDto);
}
