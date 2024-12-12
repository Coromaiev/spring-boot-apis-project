package com.springbootproject.gamesmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootproject.gamesmanagement.dtos.GameCreateDto;
import com.springbootproject.gamesmanagement.dtos.GameDetailsDto;
import com.springbootproject.gamesmanagement.dtos.GameDto;
import com.springbootproject.gamesmanagement.dtos.GameMinimalDto;
import com.springbootproject.gamesmanagement.dtos.GameUpdateDto;
import com.springbootproject.gamesmanagement.entities.GameType;

public interface IGameService {
    public GameDto getGameWithId(Long gameId);
    public List<GameDto> getAllGames();
    public List<GameMinimalDto> getAllGamesMinimal();
    public List<GameDetailsDto> getAllGamesDetails();
    public GameDetailsDto getFullGameWithId(Long gameId);
    public List<GameMinimalDto> getGamesOfHost(Long hostId);
    public List<GameMinimalDto> getGamesOfType(GameType gameType);
    public GameDto createGame(GameCreateDto newGame);
    public GameDto updateGame(GameUpdateDto updatedGame);
    public void deleteGame(Long gameId);
    public void deleteGamesOfHost(Long hostid);
}
