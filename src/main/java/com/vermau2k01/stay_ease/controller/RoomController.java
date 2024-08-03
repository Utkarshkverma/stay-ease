package com.vermau2k01.stay_ease.controller;


import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.service.IManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    private final IManagerService managerService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/manager/add-rooms")
    public ResponseEntity<Rooms> addRoom(RoomRequest rooms) {
        Rooms rooms1 = managerService.addRooms(rooms);
        return ResponseEntity.ok(rooms1);
    }
}
