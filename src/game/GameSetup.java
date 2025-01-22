package game;
import java.util.Scanner;

public class GameSetup {

    public Player[] initializePlayers() {

        Scanner scanner = new Scanner(System.in); // Crée un scanner pour lire les entrées utilisateur depuis la console

        System.out.print("Combien de joueurs participent (2 à 4) ? ");

        byte numPlayers = 0; // Stocke le nombre de joueurs choisis
        boolean isValidInput = false; // Flag pour vérifier si l'entrée est valide

        // Boucle pour demander une entrée valide à l'utilisateur
        while (!isValidInput) {
            try {
                numPlayers = scanner.nextByte(); // Lit l'entrée utilisateur
                scanner.nextLine(); // Consomme le saut de ligne
                if (numPlayers >= 2 && numPlayers <= 4) {
                    isValidInput = true; // Sort de la boucle si l'entrée est correcte
                } else {
                    System.out.print("Nombre invalide. Veuillez entrer un nombre entre 2 et 4 : ");
                }
            } catch (Exception e) {
                // Gère les cas où l'utilisateur entre un texte ou une valeur incorrecte
                System.out.print("Entrée invalide. Veuillez entrer un nombre entre 2 et 4 : ");
                scanner.nextLine(); // Consomme l'entrée incorrecte pour éviter une boucle infinie
            }
        }

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Entrez le pseudo du joueur " + (i + 1) + " : ");
            String name = scanner.nextLine(); // Lis les pseudos
            players[i] = new Player(name); // Crée un objet Player pour chaque joueur
        }

        return players;
    }
}


