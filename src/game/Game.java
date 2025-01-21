package game;

import ui.Menu;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // Crée un objet Scanner pour lire les entrées utilisateur depuis la console
        Scanner scanner = new Scanner(System.in);

        // Instancie le menu qui gère les interactions avec l'utilisateur
        Menu menu = new Menu();

        // Déclare une variable pour maintenir l'état de la boucle principale du jeu
        boolean running = true;
        while (running) {
            // Appelle la méthode pour afficher le menu et récupérer le choix de l'utilisateur
            int choice = menu.showMenu(scanner);

            // Utilise un switch pour exécuter différentes actions en fonction du choix de l'utilisateur
            switch (choice) {
                case 1:
                    // Option pour démarrer une partie (fonctionnalité à implémenter)
                    Board.initializeBoard();
                    Board.displayBoard();
                    running = false;
                    break;
                case 2:
                    // Option pour afficher les règles du jeu
                    menu.showRules();
                    break;
                case 3:
                    // Option pour afficher les scores des joueurs
                    menu.showScores();
                    break;
                case 4:
                    // Option pour quitter le jeu. Met fin à la boucle principale
                    System.out.println("Merci d'avoir joué ! À bientôt.");
                    running = false;
                    break;
                default:
                    // Gère les choix invalides
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
}