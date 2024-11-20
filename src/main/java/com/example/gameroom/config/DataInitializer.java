package com.example.gameroom.config;

import com.example.gameroom.domain.GameRoomBase;
import com.example.gameroom.repository.GameRoomBaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    public CommandLineRunner gameRoomBaseData(GameRoomBaseRepository repository) {
       return args -> {
           if (repository.findByGameType(GameRoomBase.GameType.Basic) == null) {
                GameRoomBase basicGameRoom = GameRoomBase.builder()
                        .gameType(GameRoomBase.GameType.Basic)
                        .maxPlayer(5)
                        .timeLimit(300)
                        .seedMoney(10_000_000)
                        .companies(5)
                        .is_active(false)
                        .build();

               repository.save(basicGameRoom);
           }

           if(repository.findByGameType(GameRoomBase.GameType.Contest) == null) {
               GameRoomBase contestGameRoom = GameRoomBase.builder()
                       .gameType(GameRoomBase.GameType.Contest)
                       .maxPlayer(Integer.MAX_VALUE)
                       .timeLimit(300)
                       .seedMoney(10_000_000)
                       .companies(5)
                       .is_active(false)
                       .build();

               repository.save(contestGameRoom);
           }
       };
    }

}
