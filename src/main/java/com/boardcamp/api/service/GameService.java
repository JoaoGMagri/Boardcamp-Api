package com.boardcamp.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {
    final GameRepository gameRepository;

    GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll() {
        return gameRepository.findAll();
    }

}
