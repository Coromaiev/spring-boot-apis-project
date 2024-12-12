package com.springbootproject.playermanagement.services.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.playermanagement.daos.impl.FriendDao;
import com.springbootproject.playermanagement.daos.impl.PlayerDao;
import com.springbootproject.playermanagement.dtos.FriendCreateDto;
import com.springbootproject.playermanagement.dtos.FriendDto;
import com.springbootproject.playermanagement.dtos.FriendMinimalDto;
import com.springbootproject.playermanagement.dtos.PlayerMinimalDto;
import com.springbootproject.playermanagement.entities.Friend;
import com.springbootproject.playermanagement.entities.Player;
import com.springbootproject.playermanagement.services.IFriendService;

import jakarta.persistence.NoResultException;

@Service
public class FriendService implements IFriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private PlayerDao playerDao;

    @Override
    public FriendDto getById(Long friendshipId) {
        Friend foundFriendship = friendDao.findByFriendshipId(friendshipId);
        if (foundFriendship == null) throw new IllegalArgumentException("Friendship with id " + friendshipId + " does not exist");
        return convertToDto(foundFriendship);
    }

    @Override
    public List<FriendMinimalDto> getByPlayerId(Long playerId) {
        Player player = playerDao.findByPlayerId(playerId);
        if (player == null) throw new IllegalArgumentException("Could not find player with id " + playerId);
        List<Friend> playerFriends = friendDao.findByPlayer(player);
        if (playerFriends.size() == 0) throw new NoResultException("Player " + player.getPseudonym() + " has no friends");
        return new ArrayList<FriendMinimalDto>() {{
            for (Friend friend : playerFriends) {
                add(convertToMinimalDto(friend));
            }
        }};
    }

    @Override
    public void deleteByPlayerIdAndFriendId(Long playerId, Long friendId) {
        Player player = playerDao.findByPlayerId(playerId);
        if (player == null) throw new IllegalArgumentException("Could not find player with id " + playerId);
        Player friend = playerDao.findByPlayerId(friendId);
        if (friend == null) throw new IllegalArgumentException("Could not find player with id " + friendId);
        Friend foundFriend = friendDao.findByPlayerAndFriend(player, friend);
        if (foundFriend == null) throw new IllegalArgumentException(player.getPseudonym() + " and " + friend.getPseudonym() + " are not friends yet");
        friendDao.deleteByPlayerAndFriend(player, friend);
    }

    @Override
    public FriendDto createFriend(FriendCreateDto friendDto) {
        Friend newFriend = convertToEntity(friendDto);
        return convertToDto(friendDao.save(newFriend));
    }

    private FriendDto convertToDto(Friend friend) {
        FriendDto friendDto = new FriendDto();
        PlayerMinimalDto friendMinimal = new PlayerMinimalDto();
        friendMinimal.setPlayerId(friend.getFriend().getPlayerId());
        friendMinimal.setPseudonym(friend.getFriend().getPseudonym());
        PlayerMinimalDto playerMinimal = new PlayerMinimalDto();
        playerMinimal.setPlayerId(friend.getPlayer().getPlayerId());
        playerMinimal.setPseudonym(friend.getPlayer().getPseudonym());
        friendDto.setPlayer(playerMinimal);
        friendDto.setFriendshipId(friend.getFriendshipId());
        return friendDto;
    }

    private FriendMinimalDto convertToMinimalDto(Friend friend) {
        FriendMinimalDto friendDto = new FriendMinimalDto();
        PlayerMinimalDto friendMinimal = new PlayerMinimalDto(friend.getFriend().getPlayerId(), friend.getFriend().getPseudonym());
        friendDto.setFriend(friendMinimal);
        return friendDto;
    }

    private Friend convertToEntity(FriendCreateDto friendDto) {
        Friend friend = new Friend();
        Player friendPlayer = playerDao.findByPlayerId(friendDto.getFriendId());
        Player player = playerDao.findByPlayerId(friendDto.getPlayerId());
        friend.setFriend(friendPlayer);
        friend.setPlayer(player);
        return friend;
    }

    @Override
    public void deleteById(Long id) {
        Friend foundFriend = friendDao.findByFriendshipId(id);
        if (foundFriend == null) throw new IllegalArgumentException("Friendship with id " + id + " does not exist");
        Friend otherWiseFriend = friendDao.findByPlayerAndFriend(foundFriend.getFriend(), foundFriend.getPlayer());
        friendDao.deleteById(otherWiseFriend.getFriendshipId());
        friendDao.deleteById(id);
    }
    
}
