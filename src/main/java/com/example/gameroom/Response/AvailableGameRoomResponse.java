package com.example.gameroom.Response;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;

import java.time.LocalDateTime;

public record AvailableGameRoomResponse(
    String gameRoomId,
    int participants,
    GameRooms.GameStatus status,
    LocalDateTime endTime,
    GameRoomBase gameRoomBase

) {
    public static AvailableGameRoomResponse from(GameRooms gameRooms) {
        return new AvailableGameRoomResponse(
                gameRooms.getId(),
                gameRooms.getParticipants(),
                gameRooms.getStatus(),
                gameRooms.getEndTime(),
                gameRooms.getGameRoomBase()
        );
    }

}
