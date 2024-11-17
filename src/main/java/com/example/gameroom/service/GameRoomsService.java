package com.example.gameroom.service;

import com.example.gameroom.domain.GameRooms;
import org.springframework.stereotype.Service;

@Service
public interface GameRoomsService {
    GameRooms createGameRoom(Long gameRoomBaseId, String userRole) throws Exception;
}
