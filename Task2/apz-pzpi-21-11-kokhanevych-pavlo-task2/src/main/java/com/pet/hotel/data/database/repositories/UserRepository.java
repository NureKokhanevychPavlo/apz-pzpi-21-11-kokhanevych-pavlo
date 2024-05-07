package com.pet.hotel.data.database.repositories;

import com.pet.hotel.data.database.entities.UserEntity;
import com.pet.hotel.data.models.HistoryOfRenting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT r.begin_date AS beginDate, r.end_date AS endDate, p.name AS petName, rm.number AS numberRoom, h.name AS hotelName, \n" +
            "       TIMESTAMPDIFF(HOUR, r.begin_date, r.end_date) * rm.price_hour AS totalPayment\n" +
            "FROM rent r\n" +
            "JOIN room rm ON r.room_id = rm.room_id\n" +
            "JOIN pet p ON r.pet_id = p.pet_id\n" +
            "JOIN hotel h ON rm.hotel_id = h.hotel_id\n" +
            "WHERE p.user_id = :userId", nativeQuery = true)
    Optional<List<HistoryOfRenting>> getHistoryOfRentingByUserId(@Param("userId") int userId);

    @Query(value = "SELECT * FROM user as u WHERE u.email = :email", nativeQuery = true)
    Optional<UserEntity> findUserByEmail(@Param("email") String email);
}
