package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.Rooms;
import com.vermau2k01.stay_ease.repository.RoomsRepository;
import com.vermau2k01.stay_ease.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements IManagerService{

    private final RoomsRepository roomRepository;

    @Override
    public Rooms addRooms(RoomRequest rooms) {
        Rooms build = Rooms
                .builder()
                .price(rooms.getPrice())
                .build();
        return roomRepository.save(build);
    }
}
