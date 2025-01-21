package game;

import java.io.FileWriter;
import java.io.IOException;

public class Board {

    private static String[][] board = new String[10][11];

    // Function to initialize the board
    public static void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                board[i][j] = ".";
            }
        }
    }

    // Function to display the board
    public static void displayBoard() {
        System.out.println("Game board: ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    // Function to save the score in a file
    public static void saveScore(String playerName, int score) {
        try (FileWriter writer = new FileWriter("scores.txt", true)) {
            writer.write(playerName + ": " + score + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the score.");
            e.printStackTrace();
        }
    }
}