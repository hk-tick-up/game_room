package com.example.gameroom.request;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateGameRoomsRequest(
        GameRoomBase.GameType gameType,
        String userRole
) {
    public GameRooms toEntity(GameRoomBase gameRoomBase) {
        UUID uuid = UUID.randomUUID();
        String randomCode = uuid.toString().substring(0,5).toUpperCase();
        return GameRooms.builder()
                .id(randomCode)
                .participants(1)
                .status(GameRooms.GameStatus.BEFORE_START)
                .endTime( LocalDateTime.now().plusMinutes(
                            (long) gameRoomBase.getTimeLimit() * gameRoomBase.getTurns()))
                .turn(0)
                .gameRoomBase(gameRoomBase)
                .build();
    }
}
