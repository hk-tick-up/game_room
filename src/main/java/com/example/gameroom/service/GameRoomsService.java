package com.example.gameroom.service;

import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.GameRoomsRequest;
import org.springframework.stereotype.Service;

@Service
public interface GameRoomsService {
    GameRooms createGameRoom(GameRoomsRequest request) throws Exception;
}
