package com.springbootproject.playermanagement.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "player")
public class Player {
    public static final long LEVEL_SCORE_CAP = 50;
    public static final double LEVEL_CURVE_FACTOR = 1.02;

    @Column(name = "player_id")
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "pseudonym")
    @Getter
    @Setter
    private String pseudonym;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "level")
    @Getter
    @Setter
    private Integer level;

    @Column(name = "total_points")
    @Getter
    @Setter
    private Long totalPoints;

    @Getter
    @Setter
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Friend> friends;
}

