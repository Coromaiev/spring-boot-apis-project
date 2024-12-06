package com.springbootproject.playermanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.playermanagement.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Optional<Player> findByPlayerId(Long playerId);
    public void deleteById(Long playerId);
}
