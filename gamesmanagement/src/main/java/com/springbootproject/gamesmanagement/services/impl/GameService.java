package com.springbootproject.gamesmanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.gamesmanagement.daos.impl.GameDao;
import com.springbootproject.gamesmanagement.dtos.GameCreateDto;
import com.springbootproject.gamesmanagement.dtos.GameDetailsDto;
import com.springbootproject.gamesmanagement.dtos.GameDto;
import com.springbootproject.gamesmanagement.dtos.GameMinimalDto;
import com.springbootproject.gamesmanagement.dtos.GameUpdateDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationMinimalDto;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.GameType;
import com.springbootproject.gamesmanagement.entities.Participation;
import com.springbootproject.gamesmanagement.services.IGameService;

import jakarta.persistence.NoResultException;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public GameDto getGameWithId(Long gameId) {
        Game foundGame = gameDao.findById(gameId);
        if (foundGame == null) {
            throw new IllegalArgumentException(gameId + " is not a valid game id");
        }
        return convertToDto(foundGame);
    }

    @Override
    public List<GameDto> getAllGames() {
        List<Game> games = gameDao.findAll();
        if (games.size() == 0) {
            throw new NoResultException("No games stored in database");
        }
        return new ArrayList<GameDto>() {{
            for (Game game : games) {
                add(convertToDto(game));
            }
        }};
    }

    @Override
    public List<GameMinimalDto> getAllGamesMinimal() {
        List<Game> games = gameDao.findAll();
        if (games.size() == 0) {
            throw new NoResultException("No games stored in database");
        }
        return new ArrayList<GameMinimalDto>() {{
            for (Game game : games) {
                add(convertToMinimalDto(game));
            }
        }};
    } 

    @Override
    public List<GameDetailsDto> getAllGamesDetails() {
        List<Game> games = gameDao.findAll();
        if (games.size() == 0) {
            throw new NoResultException("No Games stored in database");
        }
        return new ArrayList<GameDetailsDto>() {{
            for (Game game : games) {
                add(convertToDetailsDto(game));
            }
        }};
    }

    @Override
    public GameDetailsDto getFullGameWithId(Long gameId) {
        Game foundGame = gameDao.findById(gameId);
        if (foundGame == null) {
            throw new NoResultException("Game with id " + gameId + " not found");
        }
        return convertToDetailsDto(foundGame);
    }

    @Override
    public List<GameMinimalDto> getGamesOfHost(Long hostId) {
        List<Game> games = gameDao.findByHostId(hostId);
        if (games.size() == 0) {
            throw new NoResultException("No games found with host " + hostId);
        }
        return new ArrayList<GameMinimalDto>() {{
            for (Game game : games) {
                add(convertToMinimalDto(game));
            }
        }};
    }

    @Override
    public List<GameMinimalDto> getGamesOfType(GameType gameType) {
        List<Game> games = gameDao.findByGameType(gameType);
        if (games.size() == 0) {
            throw new NoResultException("No games found of type " + gameType.getName());
        }
        return new ArrayList<GameMinimalDto>() {{
            for (Game game : games) {
                add(convertToMinimalDto(game));
            }
        }};
    }

    @Override
    public GameDto createGame(GameCreateDto newGame) {
        Game gameToCreate = convertToEntity(newGame);
        System.out.println(gameToCreate + " in service");
        gameDao.save(gameToCreate);
        return convertToDto(gameToCreate);
    }

    @Override
    public GameDto updateGame(GameUpdateDto updatedGame) {
        Game foundGame = gameDao.findById(updatedGame.getId());
        if (foundGame == null) {
            throw new NoResultException("No game found with id " + updatedGame.getId());
        }
        foundGame.setDate(updatedGame.getDate());
        foundGame.setGameType(updatedGame.getGameType());
        foundGame.setMaxScore(updatedGame.getMaxScore());
        gameDao.save(foundGame);
        return convertToDto(foundGame);
    }

    @Override
    public void deleteGame(Long gameId) {
        Game foundGame = gameDao.findById(gameId);
        if (foundGame == null) {
            throw new IllegalArgumentException("Game with id " + gameId + " not found");
        }
        gameDao.deleteById(gameId);
    }

    @Override
    public void deleteGamesOfHost(Long hostId) {
        List<Game> games = gameDao.findByHostId(hostId);
        for (Game game : games) {
            gameDao.deleteById(game.getId());
        }
    }

    private GameDto convertToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setDate(game.getDate());
        gameDto.setGameId(game.getId());
        gameDto.setGameType(game.getGameType());
        gameDto.setMaxScore(game.getMaxScore());
        return gameDto;
    }

    private GameMinimalDto convertToMinimalDto(Game game) {
        GameMinimalDto gameDto = new GameMinimalDto();
        gameDto.setDate(game.getDate());
        gameDto.setGameId(game.getId());
        gameDto.setGameType(game.getGameType());
        return gameDto;
    }

    private GameDetailsDto convertToDetailsDto(Game game) {
        GameDetailsDto gameDto = new GameDetailsDto();
        gameDto.setDate(game.getDate());
        gameDto.setGameId(game.getId());
        gameDto.setGameType(game.getGameType());
        gameDto.setHostId(game.getHostId());
        gameDto.setMaxScore(game.getMaxScore());
        gameDto.setParticipations(new ArrayList<ParticipationMinimalDto>() {{
            for (Participation participation : game.getParticipations()) {
                ParticipationMinimalDto participationDto = new ParticipationMinimalDto();
                participationDto.setPlayerId(participation.getPlayerId());
                participationDto.setScore(participation.getScore());
                participationDto.setVictory(participation.isVictory());
                add(participationDto);
            }
        }});
        return gameDto;
    }

    private Game convertToEntity(GameCreateDto gameDto) {
        System.out.println(gameDto + " dto in conversion");
        Game newGame = new Game();
        newGame.setDate(gameDto.getDate());
        newGame.setGameType(gameDto.getGameType());
        newGame.setHostId(gameDto.getHostId());
        newGame.setMaxScore(gameDto.getMaxScore());
        newGame.setParticipations(new ArrayList<Participation>());
        System.out.println(newGame + " in conversion");
        return newGame;
    }
}
