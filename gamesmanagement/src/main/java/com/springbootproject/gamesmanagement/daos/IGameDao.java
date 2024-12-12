package com.springbootproject.gamesmanagement.daos;

import java.util.List;

import com.springbootproject.gamesmanagement.entities.Game;
import com.springbootproject.gamesmanagement.entities.GameType;

public interface IGameDao {
    public Game findById(Long id);
    public List<Game> findByGameType(GameType gameType);
    public List<Game> findByHostId(Long hostId);
    public List<Game> findAll();
    public Game save(Game game);
    public void deleteById(Long id);

}
