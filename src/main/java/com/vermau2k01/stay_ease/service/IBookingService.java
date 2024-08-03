package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    List<Rooms> availableRooms(LocalDate from, LocalDate to, Authentication authentication);
    Rooms bookRooms(LocalDate from, LocalDate to, Integer roomId,Authentication connectedUser);
}
