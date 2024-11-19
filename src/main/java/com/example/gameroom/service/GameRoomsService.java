package com.example.gameroom.service;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.CreateGameRoomsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameRoomsService {
//    GameRooms createGameRoom(CreateGameRoomsRequest request) throws Exception;
//    List<GameRooms> getGameRoomsByGameType(GameRoomBase.GameType gameType);

    GameRooms joinGameSession(CreateGameRoomsRequest request) throws Exception;

}
