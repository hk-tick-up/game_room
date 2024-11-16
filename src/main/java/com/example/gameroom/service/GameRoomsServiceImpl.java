package com.example.gameroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRoomsServiceImpl implements GameRoomsService{
    private final GameRoomBaseService gameRoomBaseService;
}
