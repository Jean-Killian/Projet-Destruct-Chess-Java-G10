package game;

import static game.GameLogic.*;
import static game.Player.playerPositions;
import static ui.Menu.*;



public class PlayerMovement {
    // Déplace un joueur
    public static boolean movePlayer(String direction, byte currentPlayer) {

        // Récupérer la position actuelle du joueur
        byte currentX = playerPositions[currentPlayer][0];
        byte currentY = playerPositions[currentPlayer][1];

        // Déterminer la nouvelle position en fonction de la direction
        byte newX = currentX, newY = currentY;

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
        board[newX][newY] = (char) ('0' + currentPlayer + 1); // Mettre à jour la nouvelle position

        // Mettre à jour la position du joueur dans le tableau
        playerPositions[currentPlayer][0] = newX;
        playerPositions[currentPlayer][1] = newY;

        return true;
    }
}