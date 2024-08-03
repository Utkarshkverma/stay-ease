package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.RoomBooking;
import com.vermau2k01.stay_ease.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

    @Query("""
      SELECT rb.room FROM RoomBooking rb
      WHERE rb.bookingFrom <= :endDate AND rb.bookingTo >= :startDate
""")
    List<Rooms> findAllBookedRooms(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);
}
