package game;

/**
 * The Player class represents a player in the game, holding their name, position, and score.
 */
public class Player {
    public String name;
    private int score;

    /**
     * Constructs a new Player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
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
