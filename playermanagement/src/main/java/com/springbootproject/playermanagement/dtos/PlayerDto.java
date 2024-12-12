package com.springbootproject.playermanagement.dtos;

import java.util.List;

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
public class PlayerDto {
    private Long playerId;
    private String name;
    private String pseudonym;
    private String email;
    private Integer level;
    private Long totalPoints;
    private List<FriendMinimalDto> friends;
}
