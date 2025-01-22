package game;
import java.util.Scanner;

import static game.BoardInitializer.initializeBoard;
import static game.CaseDestruction.destroyCase;
import static game.PlayerMovement.movePlayer;
import static ui.GameDisplay.displayBoard;

/**
 * Responsible for managing the core rules and actions in the Destruct Chess game.
 */
public class GameLogic {
    /**
     * Game board represented by a 2D char array.
     */
    public static char[][] board;      // Game board
    /**
     * Tracks which squares have been destroyed.
     */
    public static boolean[][] destroyed; // Destroyed squares
    /**
     * Indicates the currently active player (1 or 2).
     */
    public static int currentPlayer;    // Current player index

    /**
     * Initializes the board and destroyed squares, then sets the first player.
     *
     * @param players Array of players participating in the game.
     */
    // Constructor: initializes the board and destroyed squares
    public GameLogic(Player[] players) {
        this.board = new char[10][11];      // 10 rows x 11 columns
        this.destroyed = new boolean[10][11]; // All squares are intact
        initializeBoard();
        this.currentPlayer = 1; // Player 1 starts
    }

    /**
     * Main loop that handles player turns and prompts for actions.
     */
    // Start the game
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard();
            System.out.println("C'est le tour du joueur " + currentPlayer);

            // Movement
            System.out.print("Déplacez votre pièce en utilisant Z (haut), Q (gauche), S (bas), D (droite) : ");
            String direction = scanner.nextLine();
            if (movePlayer(direction)) {
                // Destruction
                String destruction;
                boolean isValid;
                do {
                    System.out.print("Entrez une case à détruire (ex: C6) : ");
                    destruction = scanner.nextLine();
                    isValid = destroyCase(destruction);
                    if (!isValid) {
                        System.out.println("destruction invalide, réessayer");
                    }
                } while (!isValid);
                // Switch to the next player
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
        scanner.close();
    }

    /* pseudo code() {
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
    public boolean isMovePossible(int x, int y) {
        if (x < 0 || x >= board.length ||y < 0 || y >= board[0].length) {
            return false;
        }
        if (destroyed[x][y] || board[x][y] != '.') {
            return false;
        }
        return true;
    }

    // Check if the current player can move
    public boolean canPlayerMove() {
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
        return isMovePossible(currentX - 1, currentY) || // Up
                isMovePossible(currentX + 1, currentY) || // Down
                isMovePossible(currentX, currentY - 1) || // Left
                isMovePossible(currentX, currentY + 1);   // Right
    }

    // Check if the game should end
    public boolean isGameOver() {
        return !canPlayerMove();
    }
}
