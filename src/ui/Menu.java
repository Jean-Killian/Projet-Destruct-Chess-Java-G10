package ui;
import java.util.Scanner;

public class Menu {

    public static void menu() {

        Scanner scanner = new Scanner(System.in);

        // Déclare une variable pour maintenir l'état de la boucle principale du jeu
        boolean running = true;
        while (running) {
            // Appelle la méthode pour afficher le menu et récupérer le choix de l'utilisateur
            int choice = showMenu(scanner);

            // Utilise un switch pour exécuter différentes actions en fonction du choix de l'utilisateur
            switch (choice) {
                case 1:
                    // Option pour démarrer une partie (fonctionnalité à implémenter)
                    askPlayerNb(scanner);
                    running = false;
                    break;
                case 2:
                    // Option pour afficher les règles du jeu
                    Rules.showRules();
                    break;
                case 3:
                    // Option pour afficher les scores des joueurs
                    Score.showScores();
                    break;
                case 4:
                    // Option pour quitter le jeu. Met fin à la boucle principale
                    System.out.println("Merci d'avoir joué ! À bientôt.");
                    running = false;
                    break;
                default:
                    // Gère les choix invalides
                    System.out.println("\n==================================");
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    System.out.println("==================================\n");
            }
        }
        // Ferme le Scanner pour libérer les ressources utilisées
        scanner.close();
    }


    // Affiche le menu et retourne le choix de l'utilisateur
    public static int showMenu(Scanner scanner) {
        System.out.println("===== Menu Principal =====");
        System.out.println("1. Démarrer une partie");
        System.out.println("2. Voir les règles");
        System.out.println("3. Voir les scores");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");

        // Gérer les entrées invalides
        while (!scanner.hasNextInt()) {
            System.out.println("\n========================================================");
            System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 4.");
            System.out.println("========================================================\n");
            System.out.print("Choisissez une option : ");
            scanner.next(); // Consomme l'entrée invalide
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne restante
        return choice;
    }



    public static int askPlayerNb(Scanner scanner) {
        System.out.println("===== Choissisez le Nombre de Joueurs =====");
        System.out.println("2 Joueurs");
        System.out.println("3 Joueurs");
        System.out.println("4 Joueurs");
        System.out.print("Choisissez une option : ");

        // Gérer les entrées invalides
        while (!scanner.hasNextInt()) {
            System.out.println("\n========================================================");
            System.out.println("Entrée invalide. Veuillez entrer un nombre entre 2 et 4.");
            System.out.println("========================================================\n");
            System.out.print("Choisissez une option : ");
            scanner.next(); // Consomme l'entrée invalide
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne restante
        return choice;
    }
}

