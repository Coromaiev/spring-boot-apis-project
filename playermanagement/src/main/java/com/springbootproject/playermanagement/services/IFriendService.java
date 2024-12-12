package com.springbootproject.playermanagement.services;

import java.util.List;

import com.springbootproject.playermanagement.dtos.FriendCreateDto;
import com.springbootproject.playermanagement.dtos.FriendDto;
import com.springbootproject.playermanagement.dtos.FriendMinimalDto;

public interface IFriendService {
    public FriendDto getById(Long friendshipId);
    public List<FriendMinimalDto> getByPlayerId(Long playerId);
    public void deleteByPlayerIdAndFriendId(Long playerId, Long friendId);
    public void deleteById(Long id);
    public FriendDto createFriend(FriendCreateDto friendDto);
}
