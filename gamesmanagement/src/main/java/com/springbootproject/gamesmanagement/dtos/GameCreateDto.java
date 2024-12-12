package com.springbootproject.gamesmanagement.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.springbootproject.gamesmanagement.entities.GameType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GameCreateDto {
    private LocalDate date;
    private int maxScore;
    private Long hostId;
    private GameType gameType;
}
