package game;

import static game.GameLogic.*;

public class PlayerMovement {
    // Déplace un joueur
    public static boolean movePlayer(String direction) {
        // Trouver la position actuelle du joueur
        int currentX = -1, currentY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == (char) ('0' + currentPlayer)) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
        }

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
                System.out.println("Direction invalide. Utilisez Z (haut), Q (gauche), S (bas), D (droite).");
                return false;
        }

        // Vérifier que la nouvelle position est valide
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
            System.out.println("Déplacement hors limites.");
            return false;
        }
        if (destroyed[newX][newY] || board[newX][newY] != '·') {
            System.out.println("Déplacement invalide. La case est détruite ou occupée.");
            return false;
        }

        // Déplacer le joueur
        board[currentX][currentY] = '.'; // Effacer l'ancienne position
        board[newX][newY] = (char) ('0' + currentPlayer); // Mettre à jour la nouvelle position
        return true;
    }
}
