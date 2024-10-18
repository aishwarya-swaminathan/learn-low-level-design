# Designing a Tic Tac Toe Game 

### &copy; Requirements Source: https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/tic-tac-toe.md

## Requirements
1. The Tic-Tac-Toe game should be played on a 3x3 grid.
2. Two players take turns marking their symbols (X or O) on the grid.
3. The first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.
4. If all the cells on the grid are filled and no player has won, the game ends in a draw.
5. The game should have a user interface to display the grid and allow players to make their moves.
6. The game should handle player turns and validate moves to ensure they are legal.
7. The game should detect and announce the winner or a draw at the end of the game.

```
Possibilities for diagonal: i==j (or) j=n-i-1

3X3 matrix

  0 1 2
0 X
1   X
2     X

4X4 matrix
  0 1 2 3
0       X
1     X
2   X
3 X

Possibilities for wins horizontally: i is constant, increment j until j=n-1
  
  0 1 2
0 X X X
1   
2     

Possibilities for wins vertically: j is constant, increment i until i=n-1

  0 1 2
0 X
1 X  
2 X    
```