package com.springbootproject.gamesmanagement.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Game {

    @Id
    @Column(name = "game_id")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @Getter
    @Setter
    private LocalDate date;

    @Column(name = "max_score")
    @Getter
    @Setter
    private int maxScore;

    @Column(name = "host_id")
    @Getter
    @Setter
    private Long hostId;

    @Column(name = "gametype")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<Participation> participations;
    
}
