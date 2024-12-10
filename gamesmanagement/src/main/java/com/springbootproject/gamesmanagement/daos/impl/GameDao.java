package com.springbootproject.gamesmanagement.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springbootproject.gamesmanagement.daos.IGameDao;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.GameType;
import com.springbootproject.gamesmanagement.repositories.GameRepository;

public class GameDao implements IGameDao {
    
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> findByGameType(GameType gameType) {
        return gameRepository.findByGameType(gameType).orElse(new ArrayList<Game>(0));
    }

    @Override
    public List<Game> findByHostId(Long hostId) {
        return gameRepository.findByHostId(hostId).orElse(new ArrayList<Game>(0));
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
