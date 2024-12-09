package com.springbootproject.gamesmanagement.daos;

import java.util.List;

import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.Participation;

public interface IParticipationDao {
    public Participation findById(Long id);
    public Participation findByPlayerIdAndGame(Long playerId, Game game);
    public List<Participation> findByPlayerId(Long playerId);
    public List<Participation> findByGame(Game game);
    public List<Participation> findByPlayerIdAndVictory(Long playerId, boolean victory);


}
