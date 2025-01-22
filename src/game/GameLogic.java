package game;
import static game.BoardInitializer.initializeBoard;
import static game.CaseDestruction.destroyCase;
import static game.PlayerMovement.movePlayer;
import java.util.Scanner;
import static ui.GameDisplay.displayBoard;
import java.util.Arrays;
import java.util.List;

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

    private Player[] players;

    /**
     * Initializes the board and destroyed squares, then sets the first player.
     *
     * @param players Array of players participating in the game.
     */
    // Constructor: initializes the board and destroyed squares
    public GameLogic(Player[] players) {
        this.players = players;
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
                displayBoard();
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

                // Check if the game is over
                if (isGameOver()) {
                    System.out.println("Le jeu est terminé!");
                    gameRunning = false;

                    // Use the actual players participating in the game
                    List<String> playerNames = Arrays.asList(players[0].getName(), players[1].getName());
                    GameScoreManager manager = new GameScoreManager();
                    manager.updateScores(playerNames);

                    // Validate scores integrity
                    if (manager.validateScores()) {
                        System.out.println("Scores integrity is valid.");
                    } else {
                        System.out.println("Scores file has been tampered with!");
                    }
                } else {
                    // Switch to the next player
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }
        }
        scanner.close();
    }

    /**
     * Checks if a move to the specified coordinates is possible.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return True if the move is possible, false otherwise.
     */
    public boolean isMovePossible(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (destroyed[x][y] || board[x][y] != '·') {
            return false;
        }
        return true;
    }

    /**
     * Checks if the current player can make any move.
     *
     * @return True if the player can move, false otherwise.
     */
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
        if (isMovePossible(currentX - 1, currentY)) { // Up
            return true;
        } else if (isMovePossible(currentX + 1, currentY)) { // Down
            return true;
        } else if (isMovePossible(currentX, currentY - 1)) { // Left
            return true;
        } else if (isMovePossible(currentX, currentY + 1)) { // Right
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the game should end.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return !canPlayerMove();
    }
}
