package com.springbootproject.gamesmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.springbootproject.gamesmanagement.dtos.ParticipationCreateDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDetailsDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationUpdateDto;
import com.springbootproject.gamesmanagement.services.impl.ParticipationService;

@RestController
@RequestMapping("/entities/participations")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @GetMapping("/{participationId}")
    public ResponseEntity<ParticipationDto> getParticipationWithId(@PathVariable Long participationId) {
        try {
            return ResponseEntity.ok(participationService.getParticipationWithId(participationId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{participationId}/details")
    public ResponseEntity<ParticipationDetailsDto> getParticipationWithDetails(@PathVariable Long participationId) {
        try {
            return ResponseEntity.ok(participationService.getParticipationDetails(participationId));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<ParticipationMinimalDto>> findByPlayerId(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(participationService.getParticipationsOfPlayer(playerId));
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ParticipationMinimalDto>> findByGame(@PathVariable Long gameId) {
        try {
            return ResponseEntity.ok(participationService.getParticipationsOfGame(gameId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{playerId}/game/{gameId}")
    public ResponseEntity<ParticipationDto> findByPlayerIdAndGame(@PathVariable Long playerId, @PathVariable Long gameId) {
        try {
            return ResponseEntity.ok(participationService.getParticipationOfPlayerForGame(playerId, gameId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{playerId}/{victory}")
    public ResponseEntity<List<ParticipationMinimalDto>> findByPlayerIdAndVictory(@PathVariable Long playerId, @PathVariable boolean victory) {
        try {
            return ResponseEntity.ok(participationService.getParticipationOfPlayerWithVictory(playerId, victory));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ParticipationDto> updateParticipation(@RequestBody ParticipationUpdateDto updateDto) {
        try {
            return ResponseEntity.ok(participationService.updateParticipation(updateDto));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ParticipationDto> createParticipation(@RequestBody ParticipationCreateDto createDto) {
        try {
            return ResponseEntity.ok(participationService.createParticipation(createDto));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ParticipationDto> deleteByParticipationId(@PathVariable Long participationid) {
        try {
            participationService.deleteParticipationWithId(participationid);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<ParticipationDto>(HttpStatus.GONE);
        }
    }

    @DeleteMapping("/delete/{playerId}/{gameId}")
    public ResponseEntity<ParticipationDto> deleteByPlayerIdAndGame(@PathVariable Long playerId, @PathVariable Long gameId) {
        try {
            participationService.deleteParticipationWithPlayerAndGame(playerId, gameId);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
}
