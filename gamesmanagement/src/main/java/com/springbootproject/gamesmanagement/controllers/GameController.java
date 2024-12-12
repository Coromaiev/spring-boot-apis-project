package com.springbootproject.gamesmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.springbootproject.gamesmanagement.dtos.GameCreateDto;
import com.springbootproject.gamesmanagement.dtos.GameDetailsDto;
import com.springbootproject.gamesmanagement.dtos.GameDto;
import com.springbootproject.gamesmanagement.dtos.GameMinimalDto;
import com.springbootproject.gamesmanagement.dtos.GameUpdateDto;
import com.springbootproject.gamesmanagement.entities.GameType;
import com.springbootproject.gamesmanagement.services.impl.GameService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("entities/games")
public class GameController {
    private static final String PLAYER_MANAGEMENT_URL = "http://localhost:7001/";
    @Autowired
    private GameService gameService;

    private WebClient webClient = WebClient.create();

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gameService.getGameWithId(id));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDto>> getAll() {
        try {
            List<GameDto> games = gameService.getAllGames();
            return ResponseEntity.ok(games);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all/minimal")
    public ResponseEntity<List<GameMinimalDto>> getAllMinimal() {
        try {
            return ResponseEntity.ok(gameService.getAllGamesMinimal());
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all/details")
    public ResponseEntity<List<GameDetailsDto>> getAllDetails() {
        try {
            return ResponseEntity.ok(gameService.getAllGamesDetails());
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<GameDetailsDto> getGameDetails(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gameService.getFullGameWithId(id));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/host/{id}")
    public ResponseEntity<List<GameMinimalDto>> getGamesOfHostWithId(@PathVariable Long id) {
        // HttpStatusCode hostExistsStatus = webClient.get()
        //     .uri(PLAYER_MANAGEMENT_URL + "entities/players/" + id)
        //     .exchangeToMono(response -> Mono.just(response.statusCode()))
        //     .block();
        // System.out.println(hostExistsStatus);
        // if (!hostExistsStatus.is2xxSuccessful()) return ResponseEntity.notFound().build();
        try {
            return ResponseEntity.ok(gameService.getGamesOfHost(id));
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/type/{gameType}")
    public ResponseEntity<List<GameMinimalDto>> getGamesOfType(@PathVariable GameType gameType) {
        try {
            return ResponseEntity.ok(gameService.getGamesOfType(gameType));
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<GameDto> createGame(@RequestBody GameCreateDto newGame) {
        if (newGame == null || newGame.getDate() == null || newGame.getGameType() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return new ResponseEntity<GameDto>(gameService.createGame(newGame), HttpStatus.CREATED);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<GameDto> updateGame(@RequestBody GameUpdateDto updatedGame) {
        try {
            return ResponseEntity.ok(gameService.updateGame(updatedGame));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameDto> deleteGameWithId(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/host/{id}")
    public ResponseEntity<GameDto> deleteGamesOfHost(@PathVariable Long id) {
        try {
            gameService.deleteGamesOfHost(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<GameDto>(HttpStatus.GONE);
        }
    }

}
