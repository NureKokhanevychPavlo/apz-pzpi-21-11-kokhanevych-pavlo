package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {


    @Query("SELECT p FROM PetEntity p JOIN FETCH p.user u WHERE u.id = :userId")
    Optional<List<PetEntity>> getPetsByUserId(@Param("userId") int userId);
}
