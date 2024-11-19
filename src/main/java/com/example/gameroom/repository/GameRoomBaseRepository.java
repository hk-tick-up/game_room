package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRoomBaseRepository extends JpaRepository<GameRoomBase, Long> {
    GameRoomBase findByGameType(GameRoomBase.GameType gameType);
//    List<GameRoomBase> findByGameTypeList(GameRoomBase.GameType gameType);
}
