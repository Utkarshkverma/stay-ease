package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Roles;
import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.entity.Users;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import com.vermau2k01.stay_ease.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ManagerServiceImpl implements IManagerService{

    private final RoomsRepository roomRepository;
    private final RolesRepository rolesRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Rooms addRooms(RoomRequest rooms, Authentication authentication) {
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

        Rooms build = Rooms
                .builder()
                .price(rooms.getPrice())
                .build();
        return roomRepository.save(build);
    }

}
