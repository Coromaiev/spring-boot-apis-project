package com.springbootproject.gamesmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participation")
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    @Getter
    @Setter
    private Long participationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    @Getter
    @Setter
    private Game game;

    @Column(name = "player_id", nullable = false)
    @Getter
    @Setter
    private Long playerId;

    @Column(name = "score")
    @Getter
    @Setter
    private int score;

    @Column(name = "victory")
    @Getter
    @Setter
    private boolean victory;
}
