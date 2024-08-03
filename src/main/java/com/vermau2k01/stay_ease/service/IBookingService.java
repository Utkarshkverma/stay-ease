package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    List<Rooms> availableRooms(LocalDate from, LocalDate to);
    Rooms bookRooms(LocalDate from, LocalDate to, Integer roomId);
}
