package com.vermau2k01.stay_ease.controller;


import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.CheckInRequest;
import com.vermau2k01.stay_ease.request.CheckOutRequest;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.response.CheckInResponse;
import com.vermau2k01.stay_ease.response.CheckOutResponse;
import com.vermau2k01.stay_ease.service.IManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final IManagerService managerService;

    @PostMapping("/manager/add-rooms")
    public ResponseEntity<Rooms> addRoom(@RequestBody @Valid RoomRequest rooms, Authentication authentication) {
        Rooms rooms1 = managerService.addRooms(rooms,authentication);
        return ResponseEntity.ok(rooms1);
    }

    @PostMapping("/manager/check-in-user")
    public ResponseEntity<List<CheckInResponse>> checkInUser(@RequestBody @Valid CheckInRequest rooms,
                                             Authentication authentication) {
        List<CheckInResponse> checkInResponses = managerService
                .checkIn(rooms, authentication);
        return ResponseEntity.ok(checkInResponses);
    }

    @PostMapping("/manager/check-out-user")
    public ResponseEntity<List<CheckOutResponse>> checkOutUser(@RequestBody @Valid CheckOutRequest rooms,Authentication authentication) {
        List<CheckOutResponse> checkOutResponses = managerService.checkOut(rooms,authentication);
        return ResponseEntity.ok(checkOutResponses);
    }
}
