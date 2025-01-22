package game;
import java.util.Scanner;

import static game.BoardInitializer.initializeBoard;
import static game.CaseDestruction.destroyCase;
import static game.PlayerMovement.movePlayer;
import static ui.GameDisplay.displayBoard;

public class GameLogic {

    public static char[][] board;      // Plateau de jeu
    public static boolean[][] destroyed; // Cases détruites
    public static int currentPlayer;    // Indice du joueur actuel

    // Constructeur : initialise le plateau et les cases détruites
    public GameLogic(Player[] players) {
        this.board = new char[10][11];      // 10 lignes x 11 colonnes
        this.destroyed = new boolean[10][11]; // Toutes les cases sont intactes
        initializeBoard();
        this.currentPlayer = 1; // Le joueur 1 commence
    }

    // Démarre la partie
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard();
            System.out.println("C'est au tour du joueur " + currentPlayer);

            // Déplacement
            System.out.print("Déplacez votre pion avec Z (haut), Q (gauche), S (bas), D (droite) : ");
            String direction = scanner.nextLine();
            if (movePlayer(direction)) {
                // Destruction
                String destruction;
                boolean isValid;
                do {
                    System.out.print("Entrez une case à détruire (ex: C6) : ");
                    destruction = scanner.nextLine();
                    isValid = destroyCase(destruction);
                    if (!isValid) {
                        System.out.println("destruction invalide, réessayer");
                    }
                } while (!isValid);
                // Passer au joueur suivant
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
        scanner.close();
    }
}

