package com.springbootproject.gamesmanagement.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.springbootproject.gamesmanagement.entities.GameType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailsDto {
    private Long gameId;
    private LocalDateTime date;
    private int maxScore;
    private Long hostId;
    private GameType gameType;
    private List<ParticipationMinimalDto> participations;
}
