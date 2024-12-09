package com.springbootproject.playermanagement.daos;

import java.util.List;

import com.springbootproject.playermanagement.entities.Friend;

public interface IFriendDao {
    public Friend findByFriendshipId(Long friendshipId);
    public List<Friend> findByPlayerId(Long playerId);
    public void deleteByPlayerIdAndFriendId(Long playerId, Long friendId);
}
