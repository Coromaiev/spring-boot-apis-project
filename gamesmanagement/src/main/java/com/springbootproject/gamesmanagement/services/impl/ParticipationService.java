package com.springbootproject.gamesmanagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springbootproject.gamesmanagement.daos.IGameDao;
import com.springbootproject.gamesmanagement.daos.IParticipationDao;
import com.springbootproject.gamesmanagement.daos.impl.ParticipationDao;
import com.springbootproject.gamesmanagement.dtos.GameMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationCreateDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDetailsDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationUpdateDto;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.Participation;
import com.springbootproject.gamesmanagement.repositories.ParticipationRepository;
import com.springbootproject.gamesmanagement.services.IParticipationService;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipationDetails'");
    }

    @Override
    public ParticipationDto getParticipationOfPlayerForGame(Long playerId, Long gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipationOfPlayerForGame'");
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationsOfPlayer(Long playerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipationsOfPlayer'");
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationsOfGame(Long gameId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipationsOfGame'");
    }

    @Override
    public List<ParticipationMinimalDto> getParticipationOfPlayerWithVictory(Long playerId, boolean victory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipationOfPlayerWithVictory'");
    }

    @Override
    public ParticipationDto createParticipation(ParticipationCreateDto newParticipation) {
        
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
