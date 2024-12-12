package com.springbootproject.gamesmanagement.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.springbootproject.gamesmanagement.entities.GameType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GameUpdateDto {
    private Long id;
    private LocalDate date;
    private int maxScore;
    private GameType gameType;
}
