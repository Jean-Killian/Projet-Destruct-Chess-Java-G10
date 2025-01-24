package game;

import static game.GameLogic.*;

public class VerifyWin {
    public static boolean isMovePossible(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (destroyed[x][y] || board[x][y] != '·') {
            return false;
        }
        return true;
    }

    // Check if the current player can move
    public static boolean canPlayerMove() {
        int currentX = -1, currentY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == (char) ('0' + currentPlayer)) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
        }
        // Check all possible moves
        if(isMovePossible(currentX - 1, currentY)) {
            return true;
        } else if (isMovePossible(currentX + 1, currentY)) {
            return true;
        } else if (isMovePossible(currentX, currentY - 1)) {
            return true;
        } else if (isMovePossible(currentX, currentY + 1)) {
            return true;
        } else {
            return false;
        }
    }

    // Check if the game should end
    public static boolean isGameOver() {
    
        if (!canPlayerMove()) {
            System.out.println("End game guys");
            return false;
        }
    
        // Check if a move is possible for the current player
        if (isMovePossible(currentPlayer, currentPlayer)) {
            return true;
        } else {
            return false;
        }
        
    }
    
}

/* pseudo code isGameOver() {
 * if the player encounters an empty square
 * then
 * the player moves
 * but
 * if the square is destroyed or occupied by a player
 * the player will be blocked and therefore cannot move
 * if one player don't move
 * game is end
 * }
 */