package com.example.gameroom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameRooms {

    @Id
    @Column(name="game_room_id")
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name="game_status")
    private GameStatus status;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Column(name="current_turn")
    private int turn;

    public enum GameStatus {
        BEFORE_START,
        IN_PROGRESS,
        COMPLETED
    }


    @Builder
    public GameRooms(GameStatus status, LocalDateTime endTime, int turn) {
        this.id = UUID.randomUUID().toString().substring(0,5);
        this.status = status;
        this.endTime = endTime;
        this.turn = turn;
    }

}
