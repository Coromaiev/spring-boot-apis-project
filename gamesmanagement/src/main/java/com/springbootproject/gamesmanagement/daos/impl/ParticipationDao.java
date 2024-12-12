package com.springbootproject.gamesmanagement.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springbootproject.gamesmanagement.daos.IParticipationDao;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.Participation;
import com.springbootproject.gamesmanagement.repositories.ParticipationRepository;

public class ParticipationDao implements IParticipationDao {
    
    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public Participation findById(Long id) {
        return participationRepository.findByParticipationId(id).orElse(null);
    }

    @Override
    public Participation findByPlayerIdAndGame(Long playerId, Game game) {
        return participationRepository.findByPlayerIdAndGame(playerId, game).orElse(null);
    }

    @Override
    public List<Participation> findByPlayerId(Long playerId) {
        return participationRepository.findByPlayerId(playerId).orElse(new ArrayList<Participation>(0));
    }

    @Override
    public List<Participation> findByGame(Game game) {
        return participationRepository.findByGame(game).orElse(new ArrayList<Participation>(0));
    }

    @Override
    public List<Participation> findByPlayerIdAndVictory(Long playerId, boolean victory) {
        return participationRepository.findByPlayerIdAndVictory(playerId, victory).orElse(new ArrayList<Participation>(0));
    }

    @Override
    public Participation save(Participation participation) {
        return participationRepository.save(participation);
    }

    @Override
    public void deleteById(Long id) {
        participationRepository.deleteById(id);
    }
    
}
