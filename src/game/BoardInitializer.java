package game;

import static game.GameLogic.board;

public class BoardInitializer {
    // Initialize the board with empty squares and players in the center
    public static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '·'; // Case vide
            }
        }
        board[5][4] = '1'; // Initial position of player 1
        board[5][6] = '2'; // Initial position of player 2
    }
}
