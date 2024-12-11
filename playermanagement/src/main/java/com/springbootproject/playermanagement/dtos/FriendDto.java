package com.springbootproject.playermanagement.dtos;

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
public class FriendDto {
    private Long friendshipId;
    private PlayerMinimalDto player;
    private PlayerMinimalDto friend;    
}
