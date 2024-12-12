package com.springbootproject.playermanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootproject.playermanagement.dtos.PlayerCreateDto;
import com.springbootproject.playermanagement.dtos.PlayerDto;
import com.springbootproject.playermanagement.dtos.PlayerMinimalDto;
import com.springbootproject.playermanagement.dtos.PlayerUpdateDto;

@Service
public interface IPlayerService {
    public List<PlayerMinimalDto> getAll();
    public PlayerDto getPlayerById(Long playerId);
    public PlayerDto getPlayerByPseudonym(String pseudonym);
    public PlayerDto getPlayerByEmail(String email);
    public PlayerDto createPlayer(PlayerCreateDto player);
    public void deleteById(Long playerId);
    public PlayerDto updatePlayer(PlayerUpdateDto playerDto);
}
