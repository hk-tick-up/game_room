package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomBaseRepository extends JpaRepository<GameRoomBase, Long> {
    GameRoomBase findByGameType(GameRoomBase.GameType gameType);
}
