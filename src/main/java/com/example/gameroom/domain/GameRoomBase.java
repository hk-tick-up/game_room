package com.example.gameroom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameRoomBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_room_base_id")
    private Long id;
//    private String game_room_id;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Column(name="max_player")
    private int maxPlayer;

    public enum GameType {

        Basic,
        Contest
    }
}
