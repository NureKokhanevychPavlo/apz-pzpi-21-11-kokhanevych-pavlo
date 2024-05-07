package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {}
