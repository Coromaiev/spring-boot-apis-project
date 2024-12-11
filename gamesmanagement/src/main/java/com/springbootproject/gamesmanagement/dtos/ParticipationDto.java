package com.springbootproject.gamesmanagement.dtos;

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
public class ParticipationDto {
    private Long participationId;
    private Long gameId;
    private Long playerId;
    private int score;
    private boolean victory;    
}
