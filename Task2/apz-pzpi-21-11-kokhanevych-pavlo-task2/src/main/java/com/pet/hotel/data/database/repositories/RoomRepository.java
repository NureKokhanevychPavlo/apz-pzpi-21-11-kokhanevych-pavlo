package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query("SELECT r FROM RoomEntity r " +
            "WHERE NOT EXISTS ( " +
            "    SELECT 1 FROM r.rents rt " +
            "    WHERE rt.room = r " +
            "    AND ( " +
            "        (:beginDate BETWEEN rt.beginDate AND rt.endDate) " +
            "        OR (:endDate BETWEEN rt.beginDate AND rt.endDate) " +
            "        OR (rt.beginDate BETWEEN :beginDate AND :endDate) " +
            "        OR (rt.endDate BETWEEN :beginDate AND :endDate) " +
            "    ) " +
            ") " +
            "AND r.hotel.id = :hotelId")
    Optional<List<RoomEntity>> getAllFreeRoomByPeriod(@Param("hotelId") int hotelId, @Param("beginDate") LocalDateTime beginDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT DISTINCT r FROM RoomEntity r JOIN FETCH r.hotel WHERE r.hotel.id = :hotelId")
    Optional<List<RoomEntity>> getAllRoomsByHotelId(@Param("hotelId") int hotelId);

    @Query("SELECT r FROM RoomEntity r WHERE r.ip = :ip")
    Optional<RoomEntity> getRoomByIP(@Param("ip") String ip);
}
