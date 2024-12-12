package com.springbootproject.playermanagement.daos;

import java.util.List;

import com.springbootproject.playermanagement.entities.Player;

public interface IPlayerDao {
    public List<Player> findAll();
    public Player findByPlayerId(Long playerId);
    public Player findByPseudonym(String pseudonym);
    public Player findByEmail(String email);
    public void deleteById(Long playerId);
    public Player save(Player player);
}
