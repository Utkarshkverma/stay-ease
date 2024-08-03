package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Roles;
import com.vermau2k01.stay_ease.entity.RoomBooking;
import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.entity.Users;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import com.vermau2k01.stay_ease.repository.RoomBookingRepository;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingServiceImpl implements IBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomsRepository roomRepository;
    private final RolesRepository rolesRepository;
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public List<Rooms> availableRooms(LocalDate from, LocalDate to,Authentication authentication) {
        Users users = (Users) authentication.getPrincipal();
        Roles requiredRole = rolesRepository
                .findByRole("USER")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));


        for (Roles role : users.getRole()) {
            logger.info("User Role: {}", role.getRole());
        }
        logger.warn("Required Role: {}", requiredRole.getRole());

        if (users.getRole() == null ||
                users.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
            throw new RuntimeException("You are not allowed to access this room");
        }
        List<Rooms> allRooms = roomRepository.findAll();
        List<Rooms> nonAvailableRooms = roomBookingRepository
                .findAllBookedRooms(from,to);
        allRooms.removeAll(nonAvailableRooms);
        return allRooms;
    }


    @Override
    public Rooms bookRooms(LocalDate from, LocalDate to, Integer roomId,Authentication connectedUser) {
        Users users = (Users) connectedUser.getPrincipal();
        Roles requiredRole = rolesRepository
                .findByRole("USER")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));


        for (Roles role : users.getRole()) {
            logger.info("User Role: {}", role.getRole());
        }
        logger.warn("Required Role: {}", requiredRole.getRole());

        if (users.getRole() == null ||
                users.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
            throw new RuntimeException("You are not allowed to access this room");
        }
        Rooms room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomBooking build = RoomBooking
                .builder()
                .bookingFrom(from)
                .bookingTo(to)
                .room(room)
                .user(users)
                .build();

        return roomBookingRepository.save(build).getRoom();
    }



}
