package com.example.gameroom.service;

import com.example.gameroom.repository.GameRoomBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRoomBaseServiceImpl implements GameRoomBaseService{
    private final GameRoomBaseRepository gameRoomBaseRepository;
}
