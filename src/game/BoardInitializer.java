package game;

import java.util.Arrays;

import static game.GameLogic.board;
import static game.Player.initializePlayer;

public class BoardInitializer {

    // Initialize the board with empty squares and players in the center
    public static void initializeBoard() {

        for (byte i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '·');  // Remplir chaque ligne avec des '·'
        }
        initializePlayer();
    }
}