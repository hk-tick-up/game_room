package com.example.gameroom.Controller;

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
        try {
            GameRooms newGameRoom = gameRoomsService.createPrivateGameRoom(request);
            return ResponseEntity.ok(newGameRoom);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("방을 생성할 수 없습니다.");
        }
    }

    @PostMapping("/join/{gameRoomId}")
    public ResponseEntity<?> joinPrivateGameRoom(@PathVariable String gameRoomId ) throws Exception {
        try {
            GameRooms gameRoom = gameRoomsService.joinPrivateGameRoom(gameRoomId);
            return ResponseEntity.ok(gameRoom);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("방에 입장할 수 없습니다.");
        }
    }


    //랜덤 게임방 입장하는 controller
    @PostMapping("/random-join")
    public ResponseEntity<?> joinOrCreateGameRoom(@RequestBody CreateGameRoomsRequest request) {
        try {
            GameRooms gameRoom = gameRoomsService.randomMatchingGameSession(request);
            return ResponseEntity.ok(gameRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("오류가 발생하였습니다.");
        }
    }


}
