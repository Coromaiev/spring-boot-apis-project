package com.springbootproject.playermanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.springbootproject.playermanagement.dtos.FriendCreateDto;
import com.springbootproject.playermanagement.dtos.FriendDto;
import com.springbootproject.playermanagement.dtos.FriendMinimalDto;
import com.springbootproject.playermanagement.services.impl.FriendService;

@RestController
@RequestMapping("/entities/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/{id}")
    public ResponseEntity<FriendDto> getById(@PathVariable Long friendshipId) {
        try {
            return ResponseEntity.ok(friendService.getById(friendshipId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<List<FriendMinimalDto>> getByPlayerId(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(friendService.getByPlayerId(playerId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/player/{playerId}/friend/{friendId}")
    public ResponseEntity<FriendDto> deleteByPlayerIdAndFriendId(@PathVariable Long playerId, @PathVariable Long friendId) {
        try {
            friendService.deleteByPlayerIdAndFriendId(playerId, friendId);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FriendDto> deleteById(@PathVariable Long id) {
        try {
            friendService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<FriendDto> createFriend(@RequestBody FriendCreateDto friendDto) {
        try {
            return new ResponseEntity<FriendDto>(friendService.createFriend(friendDto), HttpStatus.CREATED);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
