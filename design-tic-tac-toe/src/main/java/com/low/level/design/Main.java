package com.low.level.design;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
class Pair {
    int row;
    int column;

    Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Tic Tac Toe Game");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter grid size: ");
        int gridSize = sc.nextInt();
        System.out.println("Enter name of Player1: ");
        String playerName1 = sc.next();
        System.out.println("Enter symbol for Player1: ");
        Character playerSymbol1 = sc.next().charAt(0);
        System.out.println("Enter name of Player2: ");
        String playerName2 = sc.next();
        System.out.println("Enter symbol for Player2: ");
        Character playerSymbol2 = sc.next().charAt(0);
        Player player1 = new Player(playerName1, playerSymbol1);
        Player player2 = new Player(playerName2, playerSymbol2);
        GameBoard gameBoard = new GameBoard(gridSize);
        Pair selectedMove = null;

        Queue<Player> turnsQueue = new LinkedList<>();
        turnsQueue.add(player1);
        turnsQueue.add(player2);

        while(!turnsQueue.isEmpty()) {
            Player player = turnsQueue.poll();
            System.out.println(player.getPlayerName() +" - Enter your next move: ");
            selectedMove = makeNextMove(sc, gridSize, gameBoard.getGrid());
            markMoveOnGameBoard(gameBoard, selectedMove, player.getPlayerSymbol());
            printGameBoard(gameBoard);
            if(isGameOver(gameBoard, player.getPlayerSymbol())) {
                System.out.println(player.getPlayerName() + " won the game");
                break;
            } else if(isGameDrawn(gameBoard)) {
                System.out.println("Game is drawn");
                break;
            } else {
                turnsQueue.add(player);
            }
        }
    }

    private static Pair makeNextMove(Scanner sc, int gridSize, Character[][] gameGrid) {
//        System.out.println("Row: ");
//        int row = sc.nextInt();
//        System.out.println("Column: ");
//        int column = sc.nextInt();
        int row;
        int column;
        String input = sc.next();
//        if(!input.contains(",")) System.out.println("Invalid input, please enter again");
        while(!Character.isDigit(input.charAt(0)) && !Character.isDigit(input.charAt(2))) {
            System.out.println("Invalid input, please enter again");
            input = sc.next();
        }
        row = Integer.parseInt(input.split(",")[0]);
        column = Integer.parseInt(input.split(",")[1]);

        if(row <0 || row >=gridSize
                || column <0 || column >=gridSize) {
            System.out.println("Entered move is out of range, please enter a move within the range");
            return makeNextMove(sc, gridSize, gameGrid);
        } else if(gameGrid[row][column] != null) {
            System.out.println("This grid slot is already filled, select a different slot");
            return makeNextMove(sc, gridSize, gameGrid);
        } else {
            return new Pair(row, column);
        }
    }

    private static void markMoveOnGameBoard(GameBoard gameBoard, Pair pair, Character playerSymbol) {
        gameBoard.getGrid()[pair.getRow()][pair.getColumn()] = playerSymbol;
//        gameBoard.getGridCellsOccupied().add(pair);
    }

    private static boolean isGameOver(GameBoard gameBoard, Character playerSymbol) {
        return hasPlayerWonDiagonally(gameBoard, playerSymbol) || hasPlayerWonHorizontally(gameBoard, playerSymbol)
                || hasPlayerWonVertically(gameBoard, playerSymbol);
    }

    private static boolean isGameDrawn(GameBoard gameBoard) {
        return gameBoard.getGridCellsOccupied().size() == gameBoard.getGridSize() * gameBoard.getGridSize();
    }

    private static boolean hasPlayerWonDiagonally(GameBoard gameBoard, Character playerSymbol) {
        int n = gameBoard.getGridSize();
        int countPlayerMoves = 0;
        boolean isPlayerWon = false;

        // forward diagonal
        for(int i=0; i<n; i++) {
            int j=i;
            // check diagonal cell
            if(gameBoard.getGrid()[i][j]!=null && gameBoard.getGrid()[i][j].equals(playerSymbol)) {
                countPlayerMoves++;
            }
        }

        isPlayerWon = (countPlayerMoves == n);

        if(!isPlayerWon) {
            countPlayerMoves = 0;
            // reverse diagonal
            for(int i=0; i<n; i++) {
                int j=n-i-1;
                // check diagonal cell
                if(gameBoard.getGrid()[i][j]!=null && gameBoard.getGrid()[i][j].equals(playerSymbol)) {
                    countPlayerMoves++;
                }
            }
            isPlayerWon = (countPlayerMoves == n);
        }
        return isPlayerWon;
    }

    private static boolean hasPlayerWonVertically(GameBoard gameBoard, Character playerSymbol) {
        int n = gameBoard.getGridSize();
        int countPlayerMoves = 0;
        for(int j=0;j<n;j++) {
            for(int i=0; i<n; i++) {
                // check diagonal cell
                if(gameBoard.getGrid()[i][j]!=null && gameBoard.getGrid()[i][j].equals(playerSymbol)) {
                    countPlayerMoves++;
                }
            }
            if(countPlayerMoves==n) {
                return true;
            }
            countPlayerMoves = 0;
        }
        return false;
    }

    private static boolean hasPlayerWonHorizontally(GameBoard gameBoard, Character playerSymbol) {
        int n = gameBoard.getGridSize();
        int countPlayerMoves = 0;
        for(int i=0;i<n;i++) {
            for(int j=0; j<n; j++) {
                // check diagonal cell
                if(gameBoard.getGrid()[i][j]!=null && gameBoard.getGrid()[i][j].equals(playerSymbol)) {
                    countPlayerMoves++;
                }
            }
            if(countPlayerMoves==n) {
                return true;
            }
            countPlayerMoves = 0;
        }
        return false;
    }

    private static void printGameBoard(GameBoard gameBoard) {
        System.out.println("Game Board after the last move");
        System.out.println();
        int size = gameBoard.getGridSize();
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                Character value = gameBoard.getGrid()[i][j]==null? ' ': gameBoard.getGrid()[i][j];
                if(j==size-1) System.out.print(value); else System.out.print(value + " | ");
            }
            System.out.println();
            System.out.println("-- -- --");
        }
    }
}