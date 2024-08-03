package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Roles;
import com.vermau2k01.stay_ease.entity.RoomBooking;
import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.entity.Users;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import com.vermau2k01.stay_ease.repository.RoomBookingRepository;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import com.vermau2k01.stay_ease.request.CheckInRequest;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.response.CheckInResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ManagerServiceImpl implements IManagerService{

    private final RoomsRepository roomRepository;
    private final RolesRepository rolesRepository;
    private final RoomBookingRepository roomBookingRepository;
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Rooms addRooms(RoomRequest rooms, Authentication authentication) {
        try {
            Users user = (Users) authentication.getPrincipal();
            Roles requiredRole = rolesRepository
                    .findByRole("MANAGER")
                    .orElseThrow(() -> new RuntimeException("Role Not Found"));

            for (Roles role : user.getRole()) {
                logger.info("User Role: {}", role.getRole());
            }
            logger.warn("Required Role: {}", requiredRole.getRole());

            if (user.getRole() == null ||
                    user.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
                throw new RuntimeException("You are not allowed to access this room");
            }

            Rooms build = Rooms.builder()
                    .price(rooms.getPrice())
                    .build();
            return roomRepository.save(build);

        } catch (RuntimeException e) {
            logger.error("Error occurred while adding room: {}", e.getMessage(), e);
            throw e;
        }
    }



    @Override
    @Transactional
    public List<CheckInResponse> checkIn(CheckInRequest checkInRequest,
                                         Authentication connectedUser) {

        List<CheckInResponse> checkInResponses = new ArrayList<>();

        try {
            Users user = (Users) connectedUser.getPrincipal();
            Roles requiredRole = rolesRepository
                    .findByRole("MANAGER")
                    .orElseThrow(() -> new RuntimeException("Role Not Found"));

            for (Roles role : user.getRole()) {
                logger.info("User Role: {}", role.getRole());
            }
            logger.warn("Required Role: {}", requiredRole.getRole());

            if (user.getRole() == null ||
                    user.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
                throw new RuntimeException("You are not allowed to access this room");
            }

            List<RoomBooking> bookedRooms = roomBookingRepository
                    .findRoomBookingOfUser(checkInRequest.getEmailId(), LocalDate.now());
            if (bookedRooms.isEmpty()) {
                throw new RuntimeException("User has not booked rooms for today");
            }

            for (RoomBooking roomBooking : bookedRooms) {
                roomBooking.setCheckInDate(LocalDate.now());
                CheckInResponse build = CheckInResponse.builder()
                        .email(checkInRequest.getEmailId())
                        .bookingFromDate(roomBooking.getBookingFrom())
                        .bookingToDate(roomBooking.getBookingTo())
                        .checkInDate(LocalDate.now())
                        .build();
                roomBookingRepository.save(roomBooking);
                checkInResponses.add(build);
            }

        } catch (RuntimeException e) {
            logger.error("Error occurred during check-in process: {}", e.getMessage(), e);
            throw e;
        }
        logger.info("Room booked successfully");
        return checkInResponses;
    }



}
