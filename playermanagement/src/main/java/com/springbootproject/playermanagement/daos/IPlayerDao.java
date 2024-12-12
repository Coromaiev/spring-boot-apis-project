package com.springbootproject.playermanagement.daos;

import java.util.List;

import com.springbootproject.playermanagement.entities.Player;

public interface IPlayerDao {
    public Player findByPlayerId(Long player);
    public List<Player> findByPseudonym(String pseudonym);
    public List<Player> findByEmail(String Email);
    
}
