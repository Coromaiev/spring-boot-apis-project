package com.springbootproject.playermanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.playermanagement.daos.impl.PlayerDao;
import com.springbootproject.playermanagement.dtos.FriendMinimalDto;
import com.springbootproject.playermanagement.dtos.PlayerCreateDto;
import com.springbootproject.playermanagement.dtos.PlayerDto;
import com.springbootproject.playermanagement.dtos.PlayerMinimalDto;
import com.springbootproject.playermanagement.dtos.PlayerUpdateDto;
import com.springbootproject.playermanagement.entities.Friend;
import com.springbootproject.playermanagement.entities.Player;
import com.springbootproject.playermanagement.services.IPlayerService;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Override
    public List<PlayerMinimalDto> getAll() {
        return new ArrayList<PlayerMinimalDto>() {{
            for (Player player : playerDao.findAll()) {
                add(convertToMinimalDto(player));
            }
        }};
    }

    @Override
    public PlayerDto getPlayerById(Long playerId) {
        Player foundPlayer = playerDao.findByPlayerId(playerId);
        if (foundPlayer == null) throw new IllegalArgumentException("Could not find player with id " + playerId);
        return convertToDto(foundPlayer);
    }

    @Override
    public PlayerDto getPlayerByPseudonym(String pseudonym) {
        Player foundPlayer = playerDao.findByPseudonym(pseudonym);
        if (foundPlayer == null) throw new IllegalArgumentException("Could not find player with pseudo " + pseudonym);
        return convertToDto(foundPlayer);
    }

    @Override
    public PlayerDto getPlayerByEmail(String email) {
        Player foundPlayer = playerDao.findByEmail(email);
        if (foundPlayer == null) throw new IllegalArgumentException("Could not find player with email " + email);
        return convertToDto(foundPlayer);
    }

    @Override
    public PlayerDto createPlayer(PlayerCreateDto player) {
        if (playerDao.findByEmail(player.getEmail()) != null || playerDao.findByPseudonym(player.getPseudonym()) != null) {
            throw new IllegalArgumentException("Player with email " + player.getEmail() + " or pseudonym " + player.getPseudonym() + " already exists");
        }
        Player newPlayer = convertToEntity(player);
        playerDao.save(newPlayer);
        return convertToDto(newPlayer);
    }

    @Override
    public void deleteById(Long playerId) {
        Player foundPlayer = playerDao.findByPlayerId(playerId);
        if (foundPlayer == null) throw new IllegalArgumentException("Player with id " + playerId + " does not exist");
        playerDao.deleteById(playerId);
    }

    @Override
    public PlayerDto updatePlayer(PlayerUpdateDto playerDto) {
        Player foundPlayer = playerDao.findByPlayerId(playerDto.getPlayerId());
        if (foundPlayer == null) throw new IllegalArgumentException("Player not found for id " + playerDto.getPlayerId());
        Player emailPlayer = playerDao.findByEmail(playerDto.getEmail());
        if (emailPlayer != null && !emailPlayer.getPlayerId().equals(foundPlayer.getPlayerId())) throw new IllegalArgumentException(playerDto.getEmail() + " is an already used email");
        Player pseudoPlayer = playerDao.findByPseudonym(playerDto.getPseudonym());
        if (pseudoPlayer != null && !pseudoPlayer.getPlayerId().equals(foundPlayer.getPlayerId())) throw new IllegalArgumentException(playerDto.getPseudonym() + " is an already used pseudonym");

        foundPlayer.setEmail(playerDto.getEmail());
        foundPlayer.setTotalPoints(playerDto.getTotalPoints());
        foundPlayer.setName(playerDto.getName());
        foundPlayer.setPseudonym(playerDto.getPseudonym());

        int currentLevel = foundPlayer.getLevel();
        long currentPoints = foundPlayer.getTotalPoints();
        for (int i = 1; i <= currentLevel; i++) {
            currentPoints -= (i - 1) * Player.LEVEL_CURVE_FACTOR * Player.LEVEL_SCORE_CAP;
        }
        if (currentLevel * Player.LEVEL_CURVE_FACTOR * Player.LEVEL_SCORE_CAP <= currentPoints) foundPlayer.setLevel(++currentLevel);
        playerDao.save(foundPlayer);
        return convertToDto(foundPlayer);
    }

    private PlayerDto convertToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setEmail(player.getEmail());
        playerDto.setLevel(player.getLevel());
        playerDto.setName(player.getName());
        playerDto.setPseudonym(player.getPseudonym());
        playerDto.setTotalPoints(player.getTotalPoints());
        playerDto.setPlayerId(player.getPlayerId());
        playerDto.setFriends(new ArrayList<FriendMinimalDto>() {{
            for (Friend friend : player.getFriends()) {
                FriendMinimalDto friendDto = new FriendMinimalDto();
                friendDto.setFriend(convertToMinimalDto(friend.getFriend()));
                add(friendDto);
            }
        }});
        return playerDto;
    }

    private PlayerMinimalDto convertToMinimalDto(Player player) {
        PlayerMinimalDto playerDto = new PlayerMinimalDto();
        playerDto.setPlayerId(player.getPlayerId());
        playerDto.setPseudonym(player.getPseudonym());
        return playerDto;
    }

    private Player convertToEntity(PlayerCreateDto playerDto) {
        Player player = new Player();
        player.setEmail(playerDto.getEmail());
        player.setFriends(new ArrayList<Friend>());
        player.setLevel(playerDto.getLevel());
        player.setName(playerDto.getName());
        player.setPseudonym(playerDto.getPseudonym());
        player.setTotalPoints(Long.valueOf(0));
        return player;
    }
    
}
