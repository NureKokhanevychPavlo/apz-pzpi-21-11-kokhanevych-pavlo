package com.pet.hotel.businessLogic;

import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.data.database.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RepositoryAbstract {

    private final Map<Class<?>, JpaRepository<?, ?>> repositoryMap = new HashMap<>();

    @Autowired
    public RepositoryAbstract(UserRepository userRepository, PetRepository petRepository, HotelRepository hotelRepository,
                              RoomRepository roomRepository, RentEntity rentRepository, DietRepository dietRepository,
                              ScheduleRepository scheduleRepository) {
        repositoryMap.put(UserDto.class, userRepository);
        repositoryMap.put(PetDto.class, petRepository);
        repositoryMap.put(HotelDto.class, hotelRepository);
        repositoryMap.put(RoomDto.class, roomRepository);
        repositoryMap.put(RentDto.class, rentRepository);
        repositoryMap.put(DietDto.class, dietRepository);
        repositoryMap.put(ScheduleDto.class, scheduleRepository);
    }

    @SuppressWarnings("unchecked")
    public <DTO, Entity> JpaRepository<Entity, ?> getRepository(Class<DTO> dtoClass) {
        JpaRepository<Entity, ?> repository = (JpaRepository<Entity, ?>) repositoryMap.get(dtoClass);
        if (repository == null) {
            throw new IllegalArgumentException("Repository not found for DTO class: " + dtoClass.getSimpleName());
        }
        return repository;
    }
}
