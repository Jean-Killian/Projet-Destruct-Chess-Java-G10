package game;

import static game.GameLogic.board;
import static game.Player.initializePlayer;

public class BoardInitializer {
    // Initialize the board with empty squares and players in the center
    public static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '·'; // Case vide
            }
        }
        initializePlayer();
    }
}
