package com.springbootproject.gamesmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.Participation;

import java.util.List;
import java.util.Optional;


@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    public Optional<Participation> findByParticipationId(Long participationId);
    public Optional<List<Participation>> findByPlayerId(Long playerId);
    public Optional<List<Participation>> findByGame(Game game);
    public Optional<Participation> findByPlayerIdAndGame(Long playerId, Game game);
    public Optional<List<Participation>> findByPlayerIdAndVictory(Long playerId, boolean victory);
    public void deleteByParticipationId(Long participationid);
    public void deleteByPlayerIdAndGame(Long playerId, Game game);
}
