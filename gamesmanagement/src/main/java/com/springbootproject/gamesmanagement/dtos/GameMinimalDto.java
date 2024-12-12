package com.springbootproject.gamesmanagement.dtos;

import java.time.LocalDateTime;

import com.springbootproject.gamesmanagement.entities.GameType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GameMinimalDto {
    private Long gameId;
    private GameType gameType;
    private LocalDateTime date;
}
