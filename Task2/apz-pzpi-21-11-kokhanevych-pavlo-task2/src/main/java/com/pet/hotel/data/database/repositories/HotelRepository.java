package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.HotelEntity;
import com.pet.hotel.data.models.AverageDurationRenting;
import com.pet.hotel.data.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

    @Query(value = "SELECT DATE(r.begin_date) AS rentalDate,\n" +
            "       SUM(TIMESTAMPDIFF(HOUR, \n" +
            "           CASE WHEN r.begin_date < :beginDate THEN :beginDate ELSE r.begin_date END, \n" +
            "           CASE WHEN r.end_date > :endDate THEN :endDate ELSE r.end_date END) * rm.price_hour) AS totalIncome\n" +
            "FROM Rent r \n" +
            "JOIN Room rm ON r.room_id = rm.room_id \n" +
            "JOIN Hotel h ON rm.hotel_id = h.hotel_id \n" +
            "WHERE h.name = :nameHotel\n" +
            "AND ((r.begin_date >= :beginDate AND r.begin_date <= :endDate) OR \n" +
            "     (r.end_date >= :beginDate  AND r.end_date <= :endDate) OR \n" +
            "     (r.begin_date <= :beginDate  AND r.end_date >= :endDate)) \n" +
            "GROUP BY DATE(r.begin_date);", nativeQuery = true)
    Optional<List<Income>> getIncomeDuringPeriodByHotel(@Param("nameHotel") String nameHotel, @Param("beginDate") LocalDateTime beginDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT YEAR(r.begin_date) AS year, AVG(TIMESTAMPDIFF(HOUR, r.begin_date, r.end_date)) AS averageRentDuration " +
            "FROM Rent r " +
            "JOIN Room rm ON r.room_id = rm.room_id " +
            "JOIN Hotel h ON rm.hotel_id = h.hotel_id " +
            "WHERE h.name = :nameHotel " +
            "GROUP BY YEAR(r.begin_date)", nativeQuery = true)
    Optional<List<AverageDurationRenting>> getAverageDateOfRenting(@Param("nameHotel") String nameHotel);
}
