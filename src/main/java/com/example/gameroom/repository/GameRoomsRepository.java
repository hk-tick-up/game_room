package com.example.gameroom.repository;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRoomsRepository extends JpaRepository<GameRooms,String> {


}
