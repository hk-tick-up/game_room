package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRoomsRepository extends JpaRepository<GameRooms,String> {
    List<GameRooms> findByGameRoomBase_GameType(GameRoomBase.GameType gameType);
    Optional<GameRooms> findByParticipantsLessThanAndGameRoomBase_MaxPlayer(int participants, int maxPlayer);
    List<GameRooms> findGameRoomsByParticipantsLessThanAndGameRoomBaseMaxPlayer(int participants, int gameRoomBase_maxPlayer);

}
