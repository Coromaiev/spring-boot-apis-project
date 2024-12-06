package com.springbootproject.entities;

public enum GameType {
    DUEL ("Duel", 2),
    MULTIPLAYER ("Multiplayer", 10),
    SOLO ("Solo", 1);

    private GameType(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }
    
    private final String name;
    private final int maxPlayers;

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

}
