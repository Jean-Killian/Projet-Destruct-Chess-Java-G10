package game;

import java.util.HashSet;

/**
 * The Player class represents a player in the game, holding their name, position, and score.
 */
public class Player {
    private static final HashSet<String> usedNames = new HashSet<>();
    private String name;
    private int x;
    private int y;
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

    /**
     * Retrieves the player's name.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }
}

