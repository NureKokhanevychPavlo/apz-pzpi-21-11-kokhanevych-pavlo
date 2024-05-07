package com.pet.hotel.businessLogic.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper extends ModelMapper {

    public <T, D> List<T> map(List<D> dtoList, Class<T> destinationType) {
        return dtoList.stream()
                .map(obj -> super.map(obj, destinationType))
                .collect(Collectors.toList());
    }
}

