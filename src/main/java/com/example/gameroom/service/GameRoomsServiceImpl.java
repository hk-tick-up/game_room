package com.example.gameroom.service;

import com.example.gameroom.Response.AvailableGameRoomResponse;
import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.repository.GameRoomBaseRepository;
import com.example.gameroom.repository.GameRoomsRepository;
import com.example.gameroom.request.CreateGameRoomsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
//    @Autowired
    private final GameRoomsRepository gameRoomsRepository;
//    @Autowired
    private final GameRoomBaseRepository gameRoomBaseRepository;
    public AvailableGameRoomResponse availableGameRoomResponse;


    //private 방 생성 API
    public GameRooms createPrivateGameRoom(CreateGameRoomsRequest request) throws Exception {
        if (request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("ADMIN")) {
            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());

        GameRooms newGameRoom = request.toEntityPrivate(gameRoomBase);
        return gameRoomsRepository.save(newGameRoom);
    }

//    private 방 join
    public GameRooms joinPrivateGameRoom(String gameRoomId) throws Exception {
        GameRooms gameRoom = gameRoomsRepository.findById(gameRoomId).get();
        gameRoom.incrementParticipants();
        return gameRoomsRepository.save(gameRoom);
    }

    //랜덤 매칭 시스템 방이 있으면 join 아니면 create
    public GameRooms randomMatchingGameSession(CreateGameRoomsRequest request) throws Exception {
        if(request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("Admin")) {
            throw new Exception("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        GameRoomBase gameRoomType = gameRoomBaseRepository.findByGameType(request.gameType());

        if(gameRoomType == null ) {
            throw new Exception("지원하지 않는 방식입니다.");
        }

        Optional<GameRooms> availableRoom = gameRoomsRepository.findAll().stream()
                .filter(room -> room.getStatus() == GameRooms.GameStatus.BEFORE_START &&
                        room.getGameRoomBase().getGameType().equals(request.gameType()) &&
                        room.getParticipants() < room.getGameRoomBase().getMaxPlayer() &&
                        room.is_public())
                .findFirst();

        if(availableRoom.isPresent()) {
            GameRooms gameRoom = availableRoom.get();
            gameRoom.incrementParticipants();
            return gameRoomsRepository.save(gameRoom);
        }else {
            GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());
            GameRooms newGameRoom = request.toEntityPublic(gameRoomBase);
            return gameRoomsRepository.save(newGameRoom);
        }
    }


}
