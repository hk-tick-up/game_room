package com.example.gameroom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name="is_public")
    private boolean isPublic;
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

    public boolean incrementParticipants()  {
        if(this.participants < this.gameRoomBase.getMaxPlayer()) {
            this.participants++;
            return true;
        }
        return false;
    }

}
