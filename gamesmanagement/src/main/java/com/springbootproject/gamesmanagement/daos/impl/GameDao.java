package com.springbootproject.gamesmanagement.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbootproject.gamesmanagement.daos.IGameDao;
import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.GameType;
import com.springbootproject.gamesmanagement.repositories.GameRepository;

@Repository
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

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game save(Game game) {
        System.out.println(game + " in dao");
        return gameRepository.save(game);
    }
}
