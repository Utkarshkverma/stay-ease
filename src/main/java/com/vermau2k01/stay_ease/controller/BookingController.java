package com.vermau2k01.stay_ease.controller;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.BookingRequest;
import com.vermau2k01.stay_ease.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {


    private final IBookingService bookingService;


    @GetMapping("/rooms/available")
    public ResponseEntity<List<Rooms>> getAllAvailableRooms(
            @RequestParam("from") String fromDate,
            @RequestParam("to") String toDate,
            Authentication authentication
    )
    {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        List<Rooms> rooms = bookingService.availableRooms(from, to,authentication);
        return ResponseEntity.ok(rooms);
    }



    @PostMapping("/rooms/available/{id}")
    public ResponseEntity<Rooms> bookRoom(
            @PathVariable int id,
            @RequestBody BookingRequest bookingRequest, Authentication authentication
            )
    {
        Rooms rooms = bookingService.bookRooms(bookingRequest.getFrom(), bookingRequest.getTo(), id,authentication);
        return ResponseEntity.ok(rooms);
    }


}
