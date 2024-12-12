package com.springbootproject.playermanagement.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.springbootproject.playermanagement.config.WebClientConfig;
import com.springbootproject.playermanagement.dtos.PlayerCreateDto;
import com.springbootproject.playermanagement.dtos.PlayerDto;
import com.springbootproject.playermanagement.dtos.PlayerMinimalDto;
import com.springbootproject.playermanagement.dtos.PlayerUpdateDto;
import com.springbootproject.playermanagement.services.impl.PlayerService;

@RestController
@RequestMapping("/entities/players")
public class PlayerController {
    private static final String GAMES_MANAGEMENT_BASE_URL = "http://localhost:7000/";

    @Autowired
    private PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerMinimalDto>> getAll() {
        try {
            return ResponseEntity.ok(playerService.getAll());
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getById(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(playerService.getPlayerById(playerId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pseudo/{pseudonym}")
    public ResponseEntity<PlayerDto> getPlayerByPseudonym(@PathVariable String pseudonym) {
        try {
            return ResponseEntity.ok(playerService.getPlayerByPseudonym(pseudonym));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PlayerDto> getPlayerByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(playerService.getPlayerByEmail(email));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerCreateDto player) {
        try {
            return new ResponseEntity<PlayerDto>(playerService.createPlayer(player), HttpStatus.CREATED);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayerDto> deleteById(@PathVariable Long playerId) {
        try {
            playerService.deleteById(playerId);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<PlayerDto>(HttpStatus.GONE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerUpdateDto playerDto) {
        String url = GAMES_MANAGEMENT_BASE_URL + "entities/participations/player/" + playerDto.getPlayerId();
        WebClient webclient = WebClient.create();
        List<Map<String, Object>> participations = webclient.get().uri(url)
            .retrieve()
            .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
            .collectList()
            .block();
        long newTotalScore = participations.stream()
            .mapToLong(participation -> Long.valueOf((int)participation.get("score")))
            .sum();
        System.out.println("New total score : " + newTotalScore);
        playerDto.setTotalPoints(newTotalScore);

        try {
            return ResponseEntity.ok(playerService.updatePlayer(playerDto));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
