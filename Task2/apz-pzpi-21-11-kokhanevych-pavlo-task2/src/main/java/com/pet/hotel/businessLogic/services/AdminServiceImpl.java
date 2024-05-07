package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.EntityAbstract;
import com.pet.hotel.businessLogic.RepositoryAbstract;
import com.pet.hotel.businessLogic.domain.interfaces.AdminService;
import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.businessLogic.mappers.*;
import com.pet.hotel.data.database.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RentEntity rentRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private RepositoryAbstract repositoryAbstract;

    @Autowired
    private EntityAbstract entityAbstract;

    @Override
    @Async
    public List<UserDto> getAllUsers() {
        return mapper.map(userRepository.findAll(), UserDto.class);
    }

    @Override
    @Async
    public List<PetDto> getAllPets() {
        return mapper.map(petRepository.findAll(), PetDto.class);
    }

    @Override
    @Async
    public List<HotelDto> getAllHotel() {
        return mapper.map(hotelRepository.findAll(), HotelDto.class);
    }

    @Override
    @Async
    public List<RoomDto> getAllRoom() {
        return mapper.map(roomRepository.findAll(), RoomDto.class);
    }

    @Override
    @Async
    public List<RentDto> getAllRent() {
        return mapper.map(rentRepository.findAll(), RentDto.class);
    }

    @Override
    @Async
    public List<DietDto> getAllDiet() {
        return mapper.map(dietRepository.findAll(), DietDto.class);
    }

    @Override
    @Async
    public List<ScheduleDto> getAllSchedule() {
        return mapper.map(scheduleRepository.findAll(), ScheduleDto.class);
    }

    @Override
    @Async
    public <T> boolean addObject(List<T> objectsDto) {
        if (objectsDto != null && !objectsDto.isEmpty()) {
            try {
                Class<?> dtoClass = objectsDto.get(0).getClass();
                repositoryAbstract.getRepository(dtoClass).saveAll(mapper.map(objectsDto, entityAbstract.getEntity(dtoClass).getClass()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    @Async
    public <T> boolean deleteObjects(List<T> objectsDto) {
        if (objectsDto != null && !objectsDto.isEmpty()) {
            try {
                Class<?> dtoClass = objectsDto.get(0).getClass();
                repositoryAbstract.getRepository(dtoClass).saveAll(mapper.map(objectsDto, entityAbstract.getEntity(dtoClass).getClass()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
