package com.vermau2k01.stay_ease.controller;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.BookingRequest;
import com.vermau2k01.stay_ease.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {


    private final IBookingService bookingService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/rooms/available")
    public ResponseEntity<List<Rooms>> getAllAvailableRooms(
            @RequestParam("from") String fromDate,
            @RequestParam("to") String toDate
    )
    {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        List<Rooms> rooms = bookingService.availableRooms(from, to);
        return ResponseEntity.ok(rooms);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rooms/available/{id}")
    public ResponseEntity<Rooms> bookRoom(
            @PathVariable int id,
            @RequestBody BookingRequest bookingRequest
            )
    {
        Rooms rooms = bookingService.bookRooms(bookingRequest.getFrom(), bookingRequest.getTo(), id);
        return ResponseEntity.ok(rooms);
    }


}
