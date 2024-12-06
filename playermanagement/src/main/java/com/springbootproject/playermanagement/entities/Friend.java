package com.springbootproject.playermanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "Friend")
public class Friend {
    @Column(name = "friendship_id")
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long frendshipId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Player friend;


}
