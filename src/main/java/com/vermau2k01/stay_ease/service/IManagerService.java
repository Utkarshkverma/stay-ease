package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.request.CheckInRequest;
import com.vermau2k01.stay_ease.request.CheckOutRequest;
import com.vermau2k01.stay_ease.request.RoomRequest;
import com.vermau2k01.stay_ease.response.CheckInResponse;
import com.vermau2k01.stay_ease.response.CheckOutResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IManagerService {

    Rooms addRooms(RoomRequest rooms, Authentication connectedUser);
    List<CheckInResponse> checkIn(CheckInRequest checkInRequest, Authentication connectedUser);
    List<CheckOutResponse> checkOut(CheckOutRequest checkOutRequest, Authentication connectedUser);
}
