package com.example.gameroom.Controller;

import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.request.GameRoomsRequest;
import com.example.gameroom.service.GameRoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gameroom")
public class GameRoomsController {
    private final GameRoomsService gameRoomsService;

    @PostMapping
    public ResponseEntity<?> createGameRoom(@RequestBody GameRoomsRequest request){
//        try {
            GameRooms newGameRoom = gameRoomsService.createGameRoom(request);
            return ResponseEntity.ok(newGameRoom);
//        } catch (Exception e){
//            return ResponseEntity.badRequest().body("방을 생성할 수 없습니다.");
//        }
    }
}
