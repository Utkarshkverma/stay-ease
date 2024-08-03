package com.vermau2k01.stay_ease.controller;


import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.service.IManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
