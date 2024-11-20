package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRoomsRepository extends JpaRepository<GameRooms,String> {
    @Query("SELECT gr FROM GameRooms gr JOIN gr.gameRoomBase grb " +
            "WHERE gr.status = 'BEFORE_START' AND grb.gameType = 'Basic' " +
            "AND gr.participants < grb.maxPlayer AND gr.isPublic = true")
    List<GameRooms> findExistGameRooms();


}
