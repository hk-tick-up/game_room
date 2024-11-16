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
    private long id;
    private String game_room_id;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Column(name="max_player")
    private int maxPlayer = 5 ;
    @Column(name="maximum_turn")
    private int turns = 4;
    @Column(name="turn_time_limit")
    private int timeLimit = 300; //초로 환산해서 설정
    @Column(name="init_seed_money")
    private long seedMoney = 10_000_000;
    @Column(name="num_of_companies")
    private int companies;
    private boolean is_active;

    public enum GameType {
        Basic,
        Contest
    }

}
