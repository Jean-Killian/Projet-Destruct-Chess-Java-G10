package game;
import static game.BoardInitializer.initializeBoard;
import static game.CaseDestruction.destroyCase;
import static game.PlayerMovement.movePlayer;
import java.util.Scanner;
import static game.VerifyWin.isGameOver;
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
     * players Array of players participating in the game.
     */
    // Constructor: initializes the board and destroyed squares
    public GameLogic() {
        this.board = new char[10][11];      // 10 rows x 11 columns
        this.destroyed = new boolean[10][11]; // All squares are intact
        initializeBoard();
        this.currentPlayer = 1; // Player 1 starts
    }

    /**
     * Main loop that handles player turns and prompts for actions.
     */
    // Start the game
    public void startGame(Player[] players) {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {

            displayBoard();

            if (isGameOver()){
                gameRunning = false;
            }

            System.out.println("C'est le tour de " + players[currentPlayer - 1].getName());

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
}
