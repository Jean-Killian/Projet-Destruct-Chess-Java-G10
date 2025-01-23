package ui;
import game.GameLogic;
import game.GameSetup;
import game.Player;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Menu class handles the main menu interactions for the game.
 */
public class Menu {

    public static String red = "\u001B[31m";
    public static String reset = "\u001B[0m"; // Resets the color

    /**
     * Displays the main menu and handles user input for menu options.
     */
    public static void menu() {

        Scanner scanner = new Scanner(System.in); // Creates a scanner to read user input from the console
        boolean running = true;

        while (running) {
            System.out.println("=========== Menu Principal =========");
            System.out.println("1. Lancer une partie");
            System.out.println("2. Voir les règles");
            System.out.println("3. Voir les scores");
            System.out.println(red + "4. Quitter");
            System.out.println(reset + "====================================");
            System.out.print("Choisissez une option : ");

            if (!scanner.hasNext()) {
                System.out.println("No input available. Exiting menu.");
                break;
            }

            byte choice = 0;

            try {
                choice = scanner.nextByte(); // Reads user input
                scanner.nextLine(); // Consumes the newline
            } catch (InputMismatchException e) {
                System.out.println("\n====================================================");
                System.out.println(red + "Erreur : Veuillez entrer un nombre valide entre 1 et 4." + reset);
                System.out.println("====================================================\n");
                scanner.nextLine(); // Clears the buffer
                continue; // Restarts the loop without processing the input
            } catch (NoSuchElementException e) {
                System.out.println("No input available. Exiting menu.");
                break;
            }

            switch (choice) {
                case 1:
                    GameSetup setup = new GameSetup();
                    Player[] players = setup.initializePlayers(); // Asks for player info (number of players and nickname)
                    GameLogic gameLogic = new GameLogic();
                    gameLogic.startGame(players);
                    break;

                case 2:
                    Rules.showRules(); // Option to display the game rules
                    break;

                case 3:
                    Score.showScores(); // Option to display player scores
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