package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.*;
import com.vermau2k01.stay_ease.exception.OperationNotPermittedException;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import com.vermau2k01.stay_ease.repository.RoomBookingRepository;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import com.vermau2k01.stay_ease.repository.TransactionHistoryRepository;
import com.vermau2k01.stay_ease.request.CheckInRequest;
import com.vermau2k01.stay_ease.request.CheckOutRequest;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.response.CheckInResponse;
import com.vermau2k01.stay_ease.response.CheckOutResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class ManagerServiceImpl implements IManagerService{

    private final RoomsRepository roomRepository;
    private final RolesRepository rolesRepository;
    private final RoomBookingRepository roomBookingRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Rooms addRooms(RoomRequest rooms, Authentication authentication) {
        try {
            Users user = (Users) authentication.getPrincipal();
            Roles requiredRole = rolesRepository
                    .findByRole("MANAGER")
                    .orElseThrow(() -> new OperationNotPermittedException("Role Not Found"));

            for (Roles role : user.getRole()) {
                logger.info("User Role: {}", role.getRole());
            }
            logger.warn("Required Role: {}", requiredRole.getRole());

            if (user.getRole() == null ||
                    user.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
                throw new OperationNotPermittedException("You are not allowed to access this room");
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
                    .orElseThrow(() -> new OperationNotPermittedException("Role Not Found"));

            for (Roles role : user.getRole()) {
                logger.info("User Role: {}", role.getRole());
            }
            logger.warn("Required Role: {}", requiredRole.getRole());

            if (user.getRole() == null ||
                    user.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
                throw new OperationNotPermittedException("You are not allowed to access this room");
            }

            List<RoomBooking> bookedRooms = roomBookingRepository
                    .findRoomBookingOfUser(checkInRequest.getEmailId(), LocalDate.now());
            if (bookedRooms.isEmpty()) {
                throw new OperationNotPermittedException("User has not booked rooms for today");
            }

            for (RoomBooking roomBooking : bookedRooms) {
                roomBooking.setCheckInDate(LocalDate.now());
                CheckInResponse build = CheckInResponse.builder()
                        .email(checkInRequest.getEmailId())
                        .roomNo(roomBooking.getRoom().getId())
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



    @Override
    public List<CheckOutResponse> checkOut(CheckOutRequest checkOutRequest, Authentication connectedUser) {
        List<CheckOutResponse> checkOutResponses = new ArrayList<>();
        try{
            Users user = (Users) connectedUser.getPrincipal();
            Roles requiredRole = rolesRepository
                    .findByRole("MANAGER")
                    .orElseThrow(() -> new OperationNotPermittedException("Role Not Found"));

            for (Roles role : user.getRole()) {
                logger.info("User Role: {}", role.getRole());
            }
            logger.warn("Required Role: {}", requiredRole.getRole());

            if (user.getRole() == null ||
                    user.getRole().stream().noneMatch(r -> r.getRole().equals(requiredRole.getRole()))) {
                throw new OperationNotPermittedException("You are not allowed to access this room");
            }

            List<RoomBooking> bookedRooms = roomBookingRepository
                    .findRoomBookingOfUser(checkOutRequest.getEmailId(), LocalDate.now());
            if (bookedRooms.isEmpty()) {
                throw new OperationNotPermittedException("User has not booked rooms for today");
            }

            List<RoomBooking> checkOutRoom = roomBookingRepository
                    .findCheckOutRoom(checkOutRequest.getEmailId(), LocalDate.now());

            if (checkOutRoom.isEmpty()) {
                throw new OperationNotPermittedException("User has not booked rooms");
            }

            for(RoomBooking roomBooking : checkOutRoom){
                TransactionHistory build = TransactionHistory
                        .builder()
                        .email(checkOutRequest.getEmailId())
                        .roomId(roomBooking.getId())
                        .bookingFrom(roomBooking.getBookingFrom())
                        .bookingTo(roomBooking.getBookingTo())
                        .checkIn(roomBooking.getCheckInDate())
                        .checkOut(LocalDate.now())
                        .price(Objects.equals(roomBooking.getCheckInDate(), LocalDate.now()) ? 5000 : 5000 * ((int) ChronoUnit.DAYS.between(roomBooking.getCheckInDate(),LocalDate.now())))
                        .build();
                transactionHistoryRepository.save(build);

                CheckOutResponse build1 = CheckOutResponse
                        .builder()
                        .emailId(checkOutRequest.getEmailId())
                        .bookFrom(roomBooking.getBookingFrom())
                        .bookTo(roomBooking.getBookingTo())
                        .checkInDate(roomBooking.getCheckInDate())
                        .checkOutDate(LocalDate.now())
                        .price(build.getPrice())
                        .build();

                checkOutResponses.add(build1);
                roomBookingRepository.delete(roomBooking);
            }
        }
        catch (RuntimeException e) {
            logger.error("Error occurred during check-out process: {}", e.getMessage(), e);
            throw e;
        }

        return checkOutResponses;
    }


}
