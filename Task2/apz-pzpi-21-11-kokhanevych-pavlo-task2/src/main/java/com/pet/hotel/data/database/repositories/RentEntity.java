package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.models.ScheduleOfFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentEntity extends JpaRepository<com.pet.hotel.data.database.entities.RentEntity, Integer> {

    @Query( value = "SELECT s.schedule_id AS scheduleId, s.date_time AS dateTime, d.portion AS portion, d.type_food AS typeFood " +
            "FROM Schedule s " +
            "LEFT JOIN Diet d ON s.diet_id = d.diet_id " +
            "WHERE d.rent_id = :rentId", nativeQuery = true)
    Optional<List<ScheduleOfFeed>> getScheduleOfFeedById(@Param("rentId") int rentId);


    @Query("SELECT r FROM RentEntity r JOIN FETCH r.room WHERE r.room.id = :room_id AND :date BETWEEN r.beginDate AND r.endDate")
    Optional<com.pet.hotel.data.database.entities.RentEntity> findRentByDateAndRoom(@Param("room_id") int roomId, @Param("date") LocalDateTime date);
}
