package com.springbootproject.playermanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.playermanagement.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Optional<Player> findByPlayerId(Long playerId);
    public Optional<List<Player>> findByPseudonym(String pseudonym);
    public Optional<List<Player>> findByEmail(String email);
    public void deleteById(Long playerId);
}
