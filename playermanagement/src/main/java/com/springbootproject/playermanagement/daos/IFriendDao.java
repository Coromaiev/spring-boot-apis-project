package com.springbootproject.playermanagement.daos;

import java.util.List;

import com.springbootproject.playermanagement.entities.Friend;
import com.springbootproject.playermanagement.entities.Player;

public interface IFriendDao {
    public Friend findByFriendshipId(Long friendshipId);
    public List<Friend> findByPlayer(Player player);
    public Friend findByPlayerAndFriend(Player player, Player friend);
    public void deleteByPlayerAndFriend(Player player, Player friend);
    public void deleteById(Long id);
    public Friend save(Friend friend);
}
