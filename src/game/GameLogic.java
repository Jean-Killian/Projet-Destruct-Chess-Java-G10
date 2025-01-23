package game;
import static game.BoardInitializer.initializeBoard;
import static game.CaseDestruction.destroyCase;
import static game.PlayerMovement.movePlayer;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import static game.VerifyWin.isGameOver;
import static ui.GameDisplay.displayBoard;
import static ui.Menu.red;
import static ui.Menu.reset;

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
        this.currentPlayer = 0; // Player 1 starts
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

            System.out.println("C'est le tour de " + players[currentPlayer].getName());

            // Movement
            System.out.print("Déplacez votre pièce en utilisant Z (haut), Q (gauche), S (bas), D (droite) : ");
            String direction = scanner.nextLine();

            if (movePlayer(direction, (byte) currentPlayer)) {

                displayBoard();

                String destruction;
                boolean isValid;

                do {

                    System.out.print("Entrez une case à détruire (ex: C6) : ");

                    destruction = scanner.nextLine();
                    isValid = destroyCase(destruction);

                    if (!isValid) {
                        System.out.println("\n=====================================");
                        System.out.println(red + "Erreur" + reset + " : destruction invalide, réessayer");
                        System.out.println("=====================================\n");
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
                }

                else {
                    // Switch to the next player
                    if (currentPlayer == 0) {
                        currentPlayer = 1;
                    } else {
                        currentPlayer = 0;
                    }
                }
            }
        }
        scanner.close();
    }
}
