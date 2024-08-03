package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.RoomRequest;
import org.springframework.security.core.Authentication;

public interface IManagerService {

    Rooms addRooms(RoomRequest rooms, Authentication connectedUser);
}
