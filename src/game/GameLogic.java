package game;
import java.util.Scanner;

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
    private boolean[][] destroyed;      // Destroyed squares
    /**
     * Indicates the currently active player (1 or 2).
     */
    private int currentPlayer;         // Current player index

    /**
     * Initializes the board and destroyed squares, then sets the first player.
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
     * Sets up the board with empty squares and initial positions for players.
     */
    // Initialize the board with empty squares and players in the center
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.'; // Empty square
            }
        }
        board[5][2] = '1'; // Initial position of player 1
        board[5][3] = '2'; // Initial position of player 2
    }

    /**
     * Displays the current state of the board in the console.
     */
    // Display the current state of the board
    public static void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
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
            System.out.println("Player " + currentPlayer + "'s turn");

            // Movement
            System.out.print("Move your piece using W (up), A (left), S (down), D (right): ");
            String direction = scanner.nextLine();
            if (movePlayer(direction)) {
                // Destruction
                System.out.print("Enter a square to destroy (e.g., C6): ");
                String destruction = scanner.nextLine();
                if (destroyCase(destruction)) {
                    // Switch to the next player
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }
        }
        scanner.close();
    }

    /**
     * Moves the current player's piece based on the given direction input.
     * @param direction W (up), A (left), S (down), D (right)
     * @return true if the move is valid, otherwise false
     */
    // Move a player
    private boolean movePlayer(String direction) {
        // Find the current position of the player
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

        // Determine new position based on direction
        int newX = currentX, newY = currentY;
        switch (direction.toUpperCase()) {
            case "W": // Up
                newX -= 1;
                break;
            case "S": // Down
                newX += 1;
                break;
            case "A": // Left
                newY -= 1;
                break;
            case "D": // Right
                newY += 1;
                break;
            default:
                System.out.println("Direction invalide. Utilisez W (haut), A (gauche), S (bas), D (droite).");
                return false;
        }

        // Verify that the new position is valid
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
            System.out.println("Déplacement hors limites.");
            return false;
        }
        if (destroyed[newX][newY] || board[newX][newY] != '.') {
            System.out.println("Déplacement invalide. La case est détruite ou occupée.");
            return false;
        }

        // Move the player
        board[currentX][currentY] = '.'; // Clear old position
        board[newX][newY] = (char) ('0' + currentPlayer); // Update new position
        return true;
    }

    /**
     * Destroys a specified square on the board, if valid.
     * @param destruction The notation of the square to destroy (e.g., "C6")
     * @return true if the destruction is valid, otherwise false
     */
    // Destroy a square
    private boolean destroyCase(String destruction) {
        int[] pos = parsePosition(destruction);
        if (pos == null || destroyed[pos[0]][pos[1]] || board[pos[0]][pos[1]] != '.') {
            System.out.println("Destruction invalide.");
            return false;
        }

        destroyed[pos[0]][pos[1]] = true; // Mark square as destroyed
        return true;
    }

    /**
     * Converts a position string (e.g., "C5") to board indices, or returns null if invalid.
     * @param position The position string
     * @return An array of two integers [row, column], or null if invalid
     */
    // Convert position to indices (e.g., C5 -> [4][2])
    private int[] parsePosition(String position) {
        if (position.length() != 2) return null;

        char col = position.charAt(0);
        char row = position.charAt(1);

        int x = row - '1'; // Row
        int y = col - 'A'; // Column

        if (x < 0 || x >= 10 || y < 0 || y >= 11) return null;
        return new int[]{x, y};
    }
}