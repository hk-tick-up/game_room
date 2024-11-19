package com.example.gameroom.Response;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AvailableGameRoomResponse(
    String gameRoomId,
    int participants,
    GameRooms.GameStatus status,
    LocalDateTime endTime,
    int turn,
    GameRoomBase gameRoomBase

) {
    public static AvailableGameRoomResponse from(GameRooms gameRooms) {
        return new AvailableGameRoomResponse(
                gameRooms.getId(),
                gameRooms.getParticipants(),
                gameRooms.getStatus(),
                gameRooms.getEndTime(),
                gameRooms.getTurn(),
                gameRooms.getGameRoomBase()
        );
    }

}
