package com.example.gameroom.controller;

import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.CreateGameRoomsRequest;
import com.example.gameroom.service.GameRoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gameroom")
public class GameRoomsController {
    private final GameRoomsService gameRoomsService;

    @PostMapping("/create")
    public ResponseEntity<?> createPrivateGameRoom(@RequestBody CreateGameRoomsRequest request){
            GameRooms newGameRoom = gameRoomsService.createPrivateGameRoom(request);
            return ResponseEntity.ok(newGameRoom);
    }

    @PostMapping("/join/{gameRoomId}")
    public ResponseEntity<?> joinPrivateGameRoom(@PathVariable String gameRoomId ) throws Exception {
            GameRooms gameRoom = gameRoomsService.joinPrivateGameRoom(gameRoomId);
            return ResponseEntity.ok(gameRoom);
    }

    //랜덤 게임방 입장하는 controller
    @PostMapping("/random-join")
    public ResponseEntity<?> joinOrCreateGameRoom(@RequestBody CreateGameRoomsRequest request) {
            GameRooms gameRoom = gameRoomsService.randomMatchingGameSession(request);
            return ResponseEntity.ok(gameRoom);
    }
}
