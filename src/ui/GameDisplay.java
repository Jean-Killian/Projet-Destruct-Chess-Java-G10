package ui;
import static game.GameLogic.board;

/**
 * The GameDisplay class is responsible for rendering the game board and UI elements.
 */
public class GameDisplay {
    /**
     * Displays the current state of the board in the console.
     */
    // Display the current state of the board
    public static void displayBoard() {

        // Afficher les lettres des colonnes (A à K)
        System.out.print(" "); // Espace pour aligner avec les numéros de lignes
        for (char col = 'A'; col <= 'K'; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Afficher les lignes du plateau avec les numéros à droite
        for (byte i = 0; i < board.length; i++) {

            System.out.print(" "); // Alignement à gauche

            for (byte j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j] + " ");
            }
            System.out.println(i + 1); // Numéro de la ligne à droite
        }
    }
}