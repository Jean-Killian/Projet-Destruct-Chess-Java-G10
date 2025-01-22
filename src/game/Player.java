package game;

public class Player {
    private String name;
    private int x;
    private int y;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }
}
