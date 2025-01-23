package game;

import static game.GameLogic.*;
import static game.Player.playerPositions;
import static ui.Menu.*;



public class PlayerMovement {
    // Déplace un joueur
    public static boolean movePlayer(String direction, int currentPlayer) {

        // Récupérer la position actuelle du joueur
        int currentX = playerPositions[currentPlayer][0];
        int currentY = playerPositions[currentPlayer][1];

        // Déterminer la nouvelle position en fonction de la direction
        int newX = currentX, newY = currentY;
        switch (direction.toUpperCase()) {
            case "Z": // Haut
                newX -= 1;
                break;
            case "S": // Bas
                newX += 1;
                break;
            case "Q": // Gauche
                newY -= 1;
                break;
            case "D": // Droite
                newY += 1;
                break;
            default:
                System.out.println("\n====================================================");
                System.out.println(red + "Direction invalide." + reset + "Utilisez Z (haut), Q (gauche), S (bas), D (droite).");
                System.out.println("====================================================\n");
                return false;
        }

        // Vérifier si la nouvelle position est dans les limites et valide
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length ||
                board[newX][newY] != '·' || destroyed[newX][newY]) {
            System.out.println("\n====================================================");
            System.out.println(red + "Déplacement invalide." + reset + " : hors limites, case occupée ou détruite.");
            System.out.println("====================================================\n");
            return false;
        }


        // Déplacer le joueur
        board[currentX][currentY] = '·'; // Effacer l'ancienne position
        board[newX][newY] = (char) ('0' + currentPlayer); // Mettre à jour la nouvelle position

        // Mettre à jour la position du joueur dans le tableau
        playerPositions[currentPlayer][0] = newX;
        playerPositions[currentPlayer][1] = newY;

        return true;
    }
}

/*public class PlayerMovement {

    // Variables globales pour la position du joueur
    private static int currentX = 5, currentY = 5; // Position initiale du joueur (par exemple pour le joueur 1)
    private static int currentPlayer = 1; // Joueur 1 actif au début
    //private static char[][] board; // Plateau de jeu
    //private static boolean[][] destroyed; // Cases détruites

    // Tableau de directions pour optimiser la gestion des mouvements
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // Z - Haut
            {1, 0},  // S - Bas
            {0, -1}, // Q - Gauche
            {0, 1}   // D - Droite
    };

    // Déplace un joueur
    public static boolean movePlayer(String direction) {
        // Conversion de la direction en index (0 = Z, 1 = S, 2 = Q, 3 = D)
        int dirIndex = -1;
        switch (direction.toUpperCase()) {
            case "Z": dirIndex = 0; break;
            case "S": dirIndex = 1; break;
            case "Q": dirIndex = 2; break;
            case "D": dirIndex = 3; break;
            default:
                System.out.println("\n====================================================");
                System.out.println("Direction invalide. Utilisez Z (haut), Q (gauche), S (bas), D (droite).");
                System.out.println("====================================================\n");
                return false;
        }

        // Calcul des nouvelles coordonnées en fonction de la direction
        int newX = currentX + DIRECTIONS[dirIndex][0];
        int newY = currentY + DIRECTIONS[dirIndex][1];

        // Vérifier que la nouvelle position est dans les limites et valide
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length || destroyed[newX][newY] || board[newX][newY] != '·') {
            System.out.println("\n====================================================");
            System.out.println("Déplacement invalide : hors limites, case occupée ou détruite.");
            System.out.println("====================================================\n");
            return false;
        }

        // Déplacer le joueur
        board[currentX][currentY] = '·'; // Effacer l'ancienne position
        board[newX][newY] = (char) ('0' + currentPlayer); // Mettre à jour la nouvelle position

        // Mettre à jour la position actuelle du joueur
        currentX = newX;
        currentY = newY;

        return true; // Déplacement réussi
    }
}*/

