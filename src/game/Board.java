package game;

public class Board {

    private static String[][] board = new String[10][11];

    // Fonction pour initialiser le plateau
    public static void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                board[i][j] = ".";
            }
        }
    }

    // Fonction pour afficher le plateau
    public static void displayBoard() {
        System.out.println("Plateau de jeu : ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}