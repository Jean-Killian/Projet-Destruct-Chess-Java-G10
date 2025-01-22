package game;

import static game.GameLogic.board;

public class BoardInitializer {
    // Initialise le plateau avec des cases vides et les joueurs au centre
    public static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.'; // Case vide
            }
        }
        board[5][4] = '1'; // Position initiale du joueur 1
        board[5][6] = '2'; // Position initiale du joueur 2
    }
}
