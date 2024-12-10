package com.springbootproject.playermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.playermanagement.entities.Friend;
import com.springbootproject.playermanagement.entities.Player;

import java.util.List;
import java.util.Optional;


@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    public Optional<Friend> findByFrendshipId(Long frendshipId);
    public Optional<List<Friend>> findByPlayer(Player player);
    public void deleteByPlayerAndFriend(Player player, Player friend);
}
