package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, Integer> {}
