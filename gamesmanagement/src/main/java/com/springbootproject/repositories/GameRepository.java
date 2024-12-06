package com.springbootproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.entities.Game;
import com.springbootproject.entities.GameType;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    public Optional<Game> findById(Long id);
    public Optional<List<Game>> findByHostId(Long hostId);
    public Optional<List<Game>> findByGameType(GameType gameType);
    public void deleteById(Long id);
}
