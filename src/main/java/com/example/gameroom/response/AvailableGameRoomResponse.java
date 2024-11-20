package com.example.gameroom.response;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;

import java.time.LocalDateTime;

public record AvailableGameRoomResponse(
    String gameRoomId,
    int participants,
    GameRooms.GameStatus status,
    GameRoomBase gameRoomBase

) {
    public static AvailableGameRoomResponse from(GameRooms gameRooms) {
        return new AvailableGameRoomResponse(
                gameRooms.getId(),
                gameRooms.getParticipants(),
                gameRooms.getStatus(),
                gameRooms.getGameRoomBase()
        );
    }

}
