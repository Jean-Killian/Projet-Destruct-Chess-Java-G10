package game;

import java.util.Scanner;

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

        // Ask the user if they want to save the game after playing ?
        System.out.println("voulez vous sauvegarder cette partie ? (O/N)");
        try (Scanner scanner = new Scanner(System.in)) {
            String choice = scanner.nextLine();
            if (choice.equals("O")) {
                System.out.println("Partie sauvegardée !");
            } else {
                System.out.println("Partie non sauvegardée.");
            }
        }
    }
}