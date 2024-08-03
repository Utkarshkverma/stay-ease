package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
}
