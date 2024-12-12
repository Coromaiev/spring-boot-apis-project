package com.springbootproject.gamesmanagement.dtos;

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
public class ParticipationCreateDto {
    private Long playerId;
    private int score;
    private boolean victory;
    private Long gameId;
}
