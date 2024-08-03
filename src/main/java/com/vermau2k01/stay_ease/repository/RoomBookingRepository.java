package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.RoomBooking;
import com.vermau2k01.stay_ease.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

    @Query("""
      SELECT rb.room FROM RoomBooking rb
      WHERE rb.bookingFrom <= :endDate AND rb.bookingTo >= :startDate
""")
    List<Rooms> findAllBookedRooms(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

    @Query("""

            SELECT rb FROM RoomBooking rb
            WHERE rb.user.email = :email
            AND :currentDate BETWEEN rb.bookingFrom AND rb.bookingTo
""")
    List<RoomBooking> findRoomBookingOfUser(@Param("email") String email,
                                            @Param("currentDate") LocalDate
                                                        currentDate);
}
