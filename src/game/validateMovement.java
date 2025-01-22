package game;

public class validateMovement {
    public static void main(String[] args) {
        int[][] movement = {
                { 0, 1 }, // right
                { 1, 0 }, // bottom
                { 0, -1 }, // left
                { -1, 0 } // top
        };
        System.out.println(movement);
    }

    public static boolean isValidMove(int x, int y) {
        if (isValidMove(x, y)) {

        }
        return true; // Placeholder
    }
}