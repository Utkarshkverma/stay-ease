package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.RoomRequest;

public interface IManagerService {

    Rooms addRooms(RoomRequest rooms);
}
