package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {
}
