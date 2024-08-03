package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.RoomBooking;
import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.entity.Users;
import com.vermau2k01.stay_ease.repository.RoomBookingRepository;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import com.vermau2k01.stay_ease.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements IBookingService {

    private final UsersRepository usersRepository;
    private final RoomBookingRepository roomBookingRepository;
    private final RoomsRepository roomRepository;


    @Override
    public List<Rooms> availableRooms(LocalDate from, LocalDate to) {
        List<Rooms> allRooms = roomRepository.findAll();
        List<Rooms> nonAvailableRooms = roomBookingRepository
                .findAllBookedRooms(from,to);
        allRooms.removeAll(nonAvailableRooms);
        return allRooms;
    }

    @Override
    public Rooms bookRooms(LocalDate from, LocalDate to, Integer roomId) {
        Rooms room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomBooking build = RoomBooking
                .builder()
                .bookingFrom(from)
                .bookingTo(to)
                .room(room)
                .user(getMyDetails())
                .build();

        return roomBookingRepository.save(build).getRoom();
    }


    public String getUserDetails() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return authentication.getName();
    }

    public Users getMyDetails() {

        String email = getUserDetails();
        return usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }
}
