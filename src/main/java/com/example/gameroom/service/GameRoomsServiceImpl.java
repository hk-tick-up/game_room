package com.example.gameroom.service;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.repository.GameRoomBaseRepository;
import com.example.gameroom.repository.GameRoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
    private GameRoomsRepository gameRoomsRepository;
    private GameRoomBaseRepository gameRoomBaseRepository;

    public GameRooms createGameRoom(Long gameRoomBaseId, String userRole) throws Exception {
        Optional<GameRoomBase> gameRoomBaseOpt = gameRoomBaseRepository.findById(gameRoomBaseId);

        if(gameRoomBaseOpt.isEmpty()) {
            throw new Exception("방을 생성할 수 없습니다.");
        }

        GameRoomBase gameRoomBase = gameRoomBaseOpt.get();

        if(gameRoomBase.getGameType() == GameRoomBase.GameType.Contest && !userRole.equals("ADMIN")) {
            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        int maxPlayers = gameRoomBase.getGameType() == GameRoomBase.GameType.Basic ? 5 : Integer.MAX_VALUE;

        GameRooms newGameRoom = GameRooms.builder()
                .id(UUID.randomUUID().toString().substring(0,5))
                .status(GameRooms.GameStatus.BEFORE_START)
                .endTime(LocalDateTime.now().plusMinutes((long) gameRoomBase.getTimeLimit() * gameRoomBase.getTurns()))
                .turn(0)
                .gameRoomBase(gameRoomBase)
                .build();

        return gameRoomsRepository.save(newGameRoom);
    }

}
