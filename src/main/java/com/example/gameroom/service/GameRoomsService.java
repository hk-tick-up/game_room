package com.example.gameroom.service;

import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.CreateGameRoomsRequest;
import org.springframework.stereotype.Service;

@Service
public interface GameRoomsService {
    GameRooms createPrivateGameRoom(CreateGameRoomsRequest request) throws Exception;
    GameRooms randomMatchingGameSession(CreateGameRoomsRequest request) throws Exception;
    GameRooms joinPrivateGameRoom(String gameRoomId) throws Exception;
}
