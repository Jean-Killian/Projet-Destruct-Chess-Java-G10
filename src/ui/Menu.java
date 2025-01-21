package ui;

import java.util.Scanner;

public class Menu {

    // Affiche le menu et retourne le choix de l'utilisateur
    public int showMenu(Scanner scanner) {
        System.out.println("===== Menu Principal =====");
        System.out.println("1. Démarrer une partie");
        System.out.println("2. Voir les règles");
        System.out.println("3. Voir les scores");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");

        // Gérer les entrées invalides
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 4.");
            System.out.print("Choisissez une option : ");
            scanner.next(); // Consomme l'entrée invalide
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne restante
        return choice;
    }

    // Affiche les règles du jeu
    public void showRules() {
        System.out.println("\n===== Règles du jeu =====");
        System.out.println("1. Chaque joueur peut déplacer son pion d'une case (verticalement ou horizontalement).");
        System.out.println("2. Après un déplacement, une case du plateau doit être détruite.");
        System.out.println("3. Le joueur bloqué est déclaré perdant.");
        System.out.println("4. Vous ne pouvez pas occuper ou détruire une case déjà occupée.");
        System.out.println("5. Le dernier joueur à pouvoir se déplacer gagne.");
        System.out.println("=========================\n");
    }

    // Affiche les scores (sera connecté au système de gestion des scores plus tard)
    public void showScores() {
        System.out.println("\n===== Scores =====");
        System.out.println("Les scores seront bientôt disponibles !");
        System.out.println("==================\n");
    }
}

