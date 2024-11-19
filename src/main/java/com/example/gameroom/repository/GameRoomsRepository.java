package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.GameRoomsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomsRepository extends JpaRepository<GameRooms,String> {

}
