package game;

import java.util.HashSet;

import static game.GameLogic.board;

/**
 * The Player class represents a player in the game, holding their name, position, and score.
 */
public class Player {

    public static byte[][] playerPositions = new byte[2][2];

    public static void initializePlayer() {

        // Joueur 1 en (5, 4)
        board[5][4] = '1'; // Position initiale du joueur 1
        playerPositions[0][0] = 5;  // x du joueur 1
        playerPositions[0][1] = 4;  // y du joueur 1

        // Joueur 2 en (5, 6)
        board[5][6] = '2'; // Position initiale du joueur 2
        playerPositions[1][0] = 5;  // x du joueur 2
        playerPositions[1][1] = 6;  // y du joueur 2
    }

    /**
     * Retrieves the player's name.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    private static final HashSet<String> usedNames = new HashSet<>();
    private String name;
    private int score;

    /**
     * Constructs a new Player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        if (isNameTaken(name)) {
            throw new IllegalArgumentException("Player name '" + name + "' is already in use");
        }
        this.name = name;
        usedNames.add(name);
        this.score = 0;
    }

    private static boolean isNameTaken(String name) {
        return usedNames.contains(name);
    }

    /**
     * Releases the player's name when they're no longer needed.
     * Should be called when removing a player from the game.
     */
    public void dispose() {
        usedNames.remove(this.name);
    }
}

