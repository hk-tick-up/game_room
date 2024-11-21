package com.example.gameroom.service;

import com.example.gameroom.response.AvailableGameRoomResponse;
import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.domain.GameRooms;
import com.example.gameroom.repository.GameRoomBaseRepository;
import com.example.gameroom.repository.GameRoomsRepository;
import com.example.gameroom.request.CreateGameRoomsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
    private final GameRoomsRepository gameRoomsRepository;
    private final GameRoomBaseRepository gameRoomBaseRepository;
//    public AvailableGameRoomResponse availableGameRoomResponse;


    //private 방 생성 API
    public GameRooms createPrivateGameRoom(CreateGameRoomsRequest request)  {
        if (request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("ADMIN")) {
            throw new IllegalArgumentException("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        //enum에 존재하지 않는 타입을 입력했을때 에러메세지를 띄워주는게 좋은가? 굳이인거 같긴 함

        GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());
        GameRooms newGameRoom = request.toEntityPrivate(gameRoomBase);
        return gameRoomsRepository.save(newGameRoom);
    }

// private 방 join
public GameRooms joinPrivateGameRoom(String gameRoomId) {
    GameRooms gameRoom = gameRoomsRepository.findById(gameRoomId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임방입니다."));

    if (gameRoom.isPublic()) {
        throw new IllegalArgumentException("해당 게임방에는 입장할 수 없습니다.");
    }

    if (!gameRoom.incrementParticipants()) {
        throw new IllegalArgumentException("이미 최대 참여자 수입니다.");
    }

    return gameRoomsRepository.save(gameRoom);
}

    //랜덤 매칭 시스템 방이 있으면 join 아니면 create
    public GameRooms randomMatchingGameSession(CreateGameRoomsRequest request)  {
        if(request.gameType() == GameRoomBase.GameType.Contest && !request.userRole().equals("Admin")) {
            throw new IllegalArgumentException("Contest 게임 방은 관리자만 생성할 수 있습니다.");
        }

        GameRoomBase gameRoomType = gameRoomBaseRepository.findByGameType(request.gameType());

        //enum에 없는 타입일때를 예외처리 해줄 것
        if(gameRoomType == null  ) {
            throw new IllegalArgumentException("지원하지 않는 방식입니다.");
        }

        if (gameRoomType == null) {
            throw new IllegalArgumentException("지원하지 않는 방식입니다.");
        }

        List<GameRooms> availableRooms = gameRoomsRepository.findExistGameRooms();

        for (GameRooms room : availableRooms) {
            if (room.getGameRoomBase().getGameType().equals(request.gameType())) {
                room.incrementParticipants();
                return gameRoomsRepository.save(room);
            }
        }

        GameRoomBase gameRoomBase = gameRoomBaseRepository.findByGameType(request.gameType());
        GameRooms newGameRoom = request.toEntityPublic(gameRoomBase);
        return gameRoomsRepository.save(newGameRoom);
    }


}
