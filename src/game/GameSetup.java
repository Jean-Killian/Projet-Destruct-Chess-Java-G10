package game;
import java.util.Scanner;

/**
 * The GameSetup class handles the initialization of game settings, including player setup.
 */
public class GameSetup {

    /**
     * Initializes the players for the game by prompting the user for the number of players and their names.
     *
     * @return An array of Player objects initialized with user-provided names.
     */
    public Player[] initializePlayers() {

        Scanner scanner = new Scanner(System.in); // Create a scanner to read user input from console

        System.out.print("Combien de joueurs participent (2 à 4) ? ");

        byte numPlayers = 0; // Store the chosen number of players
        boolean isValidInput = false; // Flag to check if input is valid

        // Loop to request valid input from user
        while (!isValidInput) {
            try {
                numPlayers = scanner.nextByte(); // Read user input
                scanner.nextLine(); // Consume newline
                if (numPlayers >= 2 && numPlayers <= 4) {
                    isValidInput = true; // Exit loop if input is correct
                } else {
                    System.out.print("Nombre invalide. Veuillez entrer un nombre entre 2 et 4 : ");
                }
            } catch (Exception e) {
                // Handle cases where user enters text or incorrect value
                System.out.print("Entrée invalide. Veuillez entrer un nombre entre 2 et 4 : ");
                scanner.nextLine(); // Consume incorrect input to avoid infinite loop
            }
        }

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Entrez le pseudo du joueur " + (i + 1) + " : ");
            String name = scanner.nextLine(); // Read usernames
            players[i] = new Player(name); // Create a Player object for each player
        }

        return players;
    }
}


