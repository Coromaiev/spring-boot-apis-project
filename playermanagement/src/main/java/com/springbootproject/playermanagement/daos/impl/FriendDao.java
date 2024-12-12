package com.springbootproject.playermanagement.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbootproject.playermanagement.daos.IFriendDao;
import com.springbootproject.playermanagement.entities.Friend;
import com.springbootproject.playermanagement.entities.Player;
import com.springbootproject.playermanagement.repositories.FriendRepository;

@Repository
public class FriendDao implements IFriendDao {

    @Autowired
    private  FriendRepository friendRepository;

    @Override
    public Friend findByFriendshipId(Long friendshipId) {
        return friendRepository.findById(friendshipId).orElse(null);
    }

    @Override
    public List<Friend> findByPlayer(Player player) {
        return friendRepository.findByPlayer(player).orElse(new ArrayList<Friend>(0));
    }

    @Override
    public void deleteByPlayerAndFriend(Player player, Player friend) {
        friendRepository.deleteByPlayerAndFriend(player, friend);
    }

    @Override
    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void deleteById(Long id) {
        friendRepository.deleteByFriendshipId(id);
    }

    @Override
    public Friend findByPlayerAndFriend(Player player, Player friend) {
        return friendRepository.findByPlayerAndFriend(player, friend).orElse(null);
    }
    
}
