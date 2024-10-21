package com.low.level.design.tictactoe;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Player {
    int playerId;
    String playerName;
    Character playerSymbol;

    public Player(String playerName, Character playerSymbol) {
        this.playerId = new Random().nextInt();
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }
}
