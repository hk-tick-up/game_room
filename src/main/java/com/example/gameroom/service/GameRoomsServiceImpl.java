package com.example.gameroom.service;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.repository.GameRoomBaseRepository;
import com.example.gameroom.repository.GameRoomsRepository;
import com.example.gameroom.request.GameRoomsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
    @Autowired
    private GameRoomsRepository gameRoomsRepository;
    @Autowired
    private GameRoomBaseRepository gameRoomBaseRepository;

    public GameRooms createGameRoom(GameRoomsRequest request) throws Exception {
        if (request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("ADMIN")) {
            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());

        GameRooms newGameRoom = request.toEntity(gameRoomBase);
        return gameRoomsRepository.save(newGameRoom);
    }

}
