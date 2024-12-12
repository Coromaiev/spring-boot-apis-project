package com.springbootproject.gamesmanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springbootproject.gamesmanagement.daos.IGameDao;
import com.springbootproject.gamesmanagement.daos.IParticipationDao;
import com.springbootproject.gamesmanagement.dtos.GameMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationCreateDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDetailsDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationUpdateDto;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.Participation;
import com.springbootproject.gamesmanagement.services.IParticipationService;

import jakarta.persistence.NoResultException;

public class ParticipationService implements IParticipationService {

    @Autowired
    private IParticipationDao participationDao;
    @Autowired
    private IGameDao gameDao;

    @Override
    public ParticipationDto getParticipationWithId(Long id) {
        Participation foundParticipation = participationDao.findById(id);
        if (foundParticipation == null) {
            throw new IllegalArgumentException("No participation foud for id " + id);
        }
        return convertToDto(foundParticipation);
    }

    @Override
    public ParticipationDetailsDto getParticipationDetails(Long id) {
        Participation foundParticipation = participationDao.findById(id);
        if (foundParticipation == null) {
            throw new IllegalArgumentException("Participation not found for id " + id);
        }
        return convertToDetailsDto(foundParticipation);
    }

    @Override
    public ParticipationDto getParticipationOfPlayerForGame(Long playerId, Long gameId) {
        Game associatedGame = gameDao.findById(gameId);
        if (associatedGame == null) {
            throw new IllegalArgumentException("Game not found for id " + gameId);
        }
        Participation participation = participationDao.findByPlayerIdAndGame(playerId, associatedGame);
        if (participation == null) {
            throw new NoResultException("No participation found for player id " + playerId + " and game " + gameId);
        }
        return convertToDto(participation);
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationsOfPlayer(Long playerId) {
        List<Participation> participations = participationDao.findByPlayerId(playerId);
        if (participations.size() == 0) {
            throw new NoResultException("No participations found for player " + playerId);
        }
        return new ArrayList<ParticipationMinimalDto>() {{
            for (Participation participation : participations) {
                add(convertToMinimalDto(participation));
            }
        }};
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationsOfGame(Long gameId) {
        Game foundGame = gameDao.findById(gameId);
        if (foundGame == null) {
            throw new NoResultException("No game found with id " + gameId);
        }
        return new ArrayList<ParticipationMinimalDto>() {{
            for (Participation participation : participationDao.findByGame(foundGame)) {
                add(convertToMinimalDto(participation));
            }
        }};
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationOfPlayerWithVictory(Long playerId, boolean victory) {
        List<Participation> participations = participationDao.findByPlayerIdAndVictory(playerId, victory);
        if (participations.size() == 0) {
            throw new NoResultException("No game found for player with id " + playerId + " and victory status set to " + victory);
        }
        return new ArrayList<ParticipationMinimalDto>() {{
            for (Participation participation : participations) {
                add(convertToMinimalDto(participation));
            }
        }};
    }

    @Override
    public ParticipationDto createParticipation(ParticipationCreateDto newParticipation) {
        Game associatedGame = gameDao.findById(newParticipation.getGameId());
        if (associatedGame == null) {
            throw new IllegalArgumentException("Game with id " + newParticipation.getGameId() + " not found");
        }
        Participation participation = new Participation();
        participation.setGame(associatedGame);
        participation.setPlayerId(newParticipation.getPlayerId());
        participation.setScore(newParticipation.getScore());
        participation.setVictory(newParticipation.isVictory());
        return convertToDto(participation);
    }

    @Override
    public ParticipationDto updateParticipation(ParticipationUpdateDto participation) {
        Participation participationToUpdate = participationDao.findById(participation.getParticipationId());
        if (participationToUpdate == null) {
            throw new IllegalArgumentException("No participation with id " + participation.getParticipationId());
        }
        participationToUpdate.setScore(participation.getScore());
        participationToUpdate.setVictory(participation.isVictory());
        participationDao.save(participationToUpdate);
        return convertToDto(participationToUpdate);
    }

    @Override
    public void deleteParticipationWithId(Long participationId) {
        Participation foundParticipation = participationDao.findById(participationId);
        if (foundParticipation == null) {
            throw new IllegalArgumentException("No Participation found for id " + participationId);
        }
        participationDao.deleteById(participationId);
    }

    private ParticipationDto convertToDto(Participation participation) {
        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setGameId(participation.getGame().getId());
        participation.setParticipationId(participation.getParticipationId());
        participation.setPlayerId(participation.getPlayerId());
        participation.setScore(participation.getScore());
        participation.setVictory(participation.isVictory());
        return participationDto;
    }
    
    private ParticipationMinimalDto convertToMinimalDto(Participation participation) {
        ParticipationMinimalDto participationDto = new ParticipationMinimalDto();
        participationDto.setPlayerId(participation.getPlayerId());
        participationDto.setScore(participation.getScore());
        participationDto.setVictory(participation.isVictory());
        return participationDto;
    }

    private ParticipationDetailsDto convertToDetailsDto(Participation participation) {
        ParticipationDetailsDto participationDto = new ParticipationDetailsDto();
        participationDto.setGame(new GameMinimalDto() {{
            Game participationGame = participation.getGame();
            setDate(participationGame.getDate());
            setGameId(participationGame.getId());
            setGameType(participationGame.getGameType());
        }});
        participationDto.setParticipationId(participation.getParticipationId());
        participationDto.setPlayerId(participation.getPlayerId());
        participationDto.setScore(participation.getScore());
        participationDto.setVictory(participation.isVictory());
        return participationDto;

    }

    // private Participation convertToEntity(ParticipationCreateDto participationDto, Game associatedGame) {
    //     Participation participation = new Participation();
    //     participation.setGame(associatedGame);
    //     participation.setPlayerId(participationDto.getPlayerId());
    //     participation.setScore(participationDto.getScore());
    //     participation.setVictory(participationDto.isVictory());
    //     return participation;
    // }
}
