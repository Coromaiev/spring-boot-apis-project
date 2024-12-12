package com.springbootproject.playermanagement.daos.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbootproject.playermanagement.daos.IPlayerDao;
import com.springbootproject.playermanagement.entities.Player;
import com.springbootproject.playermanagement.repositories.PlayerRepository;

@Repository
public class PlayerDao implements IPlayerDao {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findByPlayerId(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    @Override
    public Player findByPseudonym(String pseudonym) {
        return playerRepository.findByPseudonym(pseudonym).orElse(null);
    }

    @Override
    public Player findByEmail(String email) {
        return playerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteById(Long playerId) {
        playerRepository.deleteById(playerId);
    }
    
}
