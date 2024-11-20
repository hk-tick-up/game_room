package com.example.gameroom.service;

import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.CreateGameRoomsRequest;
import org.springframework.stereotype.Service;

@Service
public interface GameRoomsService {
    GameRooms createPrivateGameRoom(CreateGameRoomsRequest request);
    GameRooms randomMatchingGameSession(CreateGameRoomsRequest request);
    GameRooms joinPrivateGameRoom(String gameRoomId);
}
