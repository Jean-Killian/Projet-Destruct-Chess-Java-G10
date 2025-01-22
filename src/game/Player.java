package game;

/**
 * The Player class represents a player in the game, holding their name, position, and score.
 */
public class Player {
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

    /**
     * Sets the player's position on the board.
     *
     * @param x The x-coordinate of the new position.
     * @param y The y-coordinate of the new position.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the player's current position.
     *
     * @return An array containing the x and y coordinates of the player's position.
     */
    public int[] getPosition() {
        return new int[]{x, y};
    }

    /**
     * Adds points to the player's score.
     *
     * @param points The number of points to add.
     */
    public void addScore(int points) {
        this.score += points;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }
}
