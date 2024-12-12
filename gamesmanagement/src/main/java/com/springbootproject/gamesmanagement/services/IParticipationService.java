package com.springbootproject.gamesmanagement.services;

import java.util.List;

import com.springbootproject.gamesmanagement.dtos.ParticipationCreateDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDetailsDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationMinimalDto;
import com.springbootproject.gamesmanagement.dtos.ParticipationUpdateDto;

public interface IParticipationService {
    public ParticipationDto getParticipationWithId(Long id);
    public ParticipationDetailsDto getParticipationDetails(Long id);
    public ParticipationDto getParticipationOfPlayerForGame(Long playerId, Long gameId);
    public List<ParticipationMinimalDto> getParticipationsOfPlayer(Long playerId);
    public List<ParticipationMinimalDto> getParticipationsOfGame(Long gameId);
    public List<ParticipationMinimalDto> getParticipationOfPlayerWithVictory(Long playerId, boolean victory);
    public ParticipationDto createParticipation(ParticipationCreateDto newParticipation);
    public ParticipationDto updateParticipation(ParticipationUpdateDto participation);
    public void deleteParticipationWithId(Long participationId);
}
