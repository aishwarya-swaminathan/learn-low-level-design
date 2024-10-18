package com.low.level.design;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameBoard {
    Integer gridSize;
    Character[][] grid;

    public GameBoard(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new Character[gridSize][gridSize];
    }
}
