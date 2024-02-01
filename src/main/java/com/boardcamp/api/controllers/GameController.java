package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
    
    final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameModel> getGames() {
        return gameService.findAll();
    }

}
