package com.example.gameroom.service;

import com.example.gameroom.Response.AvailableGameRoomResponse;
import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.repository.GameRoomBaseRepository;
import com.example.gameroom.repository.GameRoomsRepository;
import com.example.gameroom.request.CreateGameRoomsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
    @Autowired
    private GameRoomsRepository gameRoomsRepository;
    @Autowired
    private GameRoomBaseRepository gameRoomBaseRepository;
    public AvailableGameRoomResponse availableGameRoomResponse;


    //방 생성 API -> 수정할 부분: 방을 찾아서 빈 방이 있으면 참여하게 하고 아니면 생성하게 할 것
//    public GameRooms createGameRoom(GameRoomsRequest request) throws Exception {
//        if (request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("ADMIN")) {
//            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
//        }
//
//        GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());
//
//        GameRooms newGameRoom = request.toEntity(gameRoomBase);
//        return gameRoomsRepository.save(newGameRoom);
//    }

    public GameRooms joinGameSession(CreateGameRoomsRequest request) throws Exception {
        if(request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("Admin")) {
            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        GameRoomBase gameRoomType = gameRoomBaseRepository.findByGameType(request.gameType());

        if(gameRoomType == null ) {
            throw new Exception("지원하지 않는 방식입니다.");
        }

        Optional<GameRooms> availableRoom = gameRoomsRepository.findAll().stream()
                .filter(room -> room.getStatus() == GameRooms.GameStatus.BEFORE_START &&
                        room.getGameRoomBase().getGameType().equals(request.gameType())
                        && room.getParticipants() < room.getGameRoomBase().getMaxPlayer()).findFirst();

        if(availableRoom.isPresent()) {
            GameRooms gameRoom = availableRoom.get();
            gameRoom.incrementParticipants();
            return gameRoomsRepository.save(gameRoom);
        }else {
            GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());
            GameRooms newGameRoom = request.toEntity(gameRoomBase);
            return gameRoomsRepository.save(newGameRoom);
        }
    }


}
