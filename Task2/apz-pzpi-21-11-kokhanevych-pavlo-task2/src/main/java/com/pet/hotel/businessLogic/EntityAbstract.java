package com.pet.hotel.businessLogic;

import com.pet.hotel.businessLogic.domain.transferObjects.DietDto;
import com.pet.hotel.businessLogic.domain.transferObjects.PetDto;
import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.ScheduleDto;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.data.database.entities.*;
import com.pet.hotel.data.database.repositories.RentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EntityAbstract {

    private final Map<Class<?>, Object> entityMap = new HashMap<>();

    @Autowired
    public EntityAbstract(UserEntity userEntity, PetEntity petEntity, HotelEntity hotelEntity,
                          RoomEntity roomEntity, RentEntity rentEntity, DietEntity dietEntity,
                          ScheduleEntity scheduleEntity) {
        entityMap.put(UserDto.class, userEntity);
        entityMap.put(PetDto.class, petEntity);
        entityMap.put(HotelDto.class, hotelEntity);
        entityMap.put(RoomDto.class, roomEntity);
        entityMap.put(RentDto.class, rentEntity);
        entityMap.put(DietDto.class, dietEntity);
        entityMap.put(ScheduleDto.class, scheduleEntity);
    }

    @SuppressWarnings("unchecked")
    public <DTO> Object getEntity(Class<DTO> dtoClass) {
        Object entity = entityMap.get(dtoClass);
        if (entity == null) {
            throw new IllegalArgumentException("Entity not found for DTO class: " + dtoClass.getSimpleName());
        }
        return entity;
    }
}
