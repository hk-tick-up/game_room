package com.example.gameroom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name="participants")
    private int participants;
    @Enumerated(EnumType.STRING)
    @Column(name="game_status")
    private GameStatus status;
    @Column(name="end_time")
    private LocalDateTime endTime;
    private boolean is_public;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_room_base_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private GameRoomBase gameRoomBase;


    public enum GameStatus {
        BEFORE_START,
        IN_PROGRESS,
        COMPLETED
    }

//    @Builder
//    public GameRooms(GameStatus status, LocalDateTime endTime, int turn, int participants,GameRoomBase gameRoomBase) {
//        UUID uuid = UUID.randomUUID();
//        this.id = uuid.toString().substring(0,5).toUpperCase();
//        this.status = status;
//        this.endTime = endTime;
//        this.turn = turn;
//        this.participants = participants;
//        this.gameRoomBase = gameRoomBase;
//    }

    public void incrementParticipants() throws Exception {
        if(this.participants < this.gameRoomBase.getMaxPlayer()) {
            this.participants++;
        } else {
            throw new Exception("이미 최대 참여자 수입니다.");
        }
    }

}
