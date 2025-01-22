package ui;
import game.GameLogic;
import game.GameSetup;
import game.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void menu() {

        String red = "\u001B[31m";
        String reset = "\u001B[0m"; // Réinitialise la couleur

        Scanner scanner = new Scanner(System.in); //crée un scanner pour lire les entrées de l'utilisateur depuis la console
        boolean running = true;

        while (running) {
            System.out.println("=========== Menu Principal =========");
            System.out.println("1. Lancer une partie");
            System.out.println("2. Voir les règles");
            System.out.println("3. Voir les scores");
            System.out.println(red + "4. Quitter");
            System.out.println(reset + "====================================");
            System.out.print("Choisissez une option : ");

            byte choice = 0;

            try {
                choice = scanner.nextByte(); // Lecture de l'entrée utilisateur
                scanner.nextLine(); // Consomme le saut de ligne
            } catch (InputMismatchException e) {
                System.out.println("\n====================================================");
                System.out.println(red + "Erreur : Veuillez entrer un nombre valide entre 1 et 4." + reset);
                System.out.println("====================================================\n");
                scanner.nextLine(); // Nettoie le buffer
                continue; // Reprend la boucle sans traiter l'entrée
            }

            switch (choice) {
                case 1:
                    GameSetup setup = new GameSetup();
                    Player[] players = setup.initializePlayers(); //demande les info au joueur (nombre de joueur et pseudo)
                    GameLogic gameLogic = new GameLogic(players);
                    gameLogic.startGame();
                    break;

                case 2:
                    Rules.showRules(); // Option pour afficher les règles du jeu
                    break;

                case 3:
                    Score.showScores(); // Option pour afficher les scores des joueurs
                    break;

                case 4:
                    System.out.println("Merci d'avoir joué ! À bientôt."); // Option pour quitter le jeu. Met fin à la boucle principale
                    running = false;
                    break;

                default:
                    System.out.println("\n=====================================");
                    System.out.println(red + "Option invalide, veuillez réessayer." + reset);
                    System.out.println("=====================================\n");
            }
        }
        scanner.close();
    }
}