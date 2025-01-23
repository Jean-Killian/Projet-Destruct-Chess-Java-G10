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

        Scanner scanner = new Scanner(System.in);

        byte numPlayers = 0;

        do {
            System.out.print("Combien de joueurs participent (2 à 4) ? ");

            while (!scanner.hasNextByte()) {

                System.out.print("Entrée invalide. Veuillez entrer un nombre entre 2 et 4 : ");
                scanner.next(); // Consomme la mauvaise entrée
            }
            numPlayers = scanner.nextByte();
            scanner.nextLine();

        } while (numPlayers < 2 || numPlayers > 4);

        Player[] players = new Player[numPlayers];

        for (byte i = 0; i < numPlayers; i++) {

            System.out.print("Entrez le pseudo du joueur " + (i + 1) + " : ");
            String name = scanner.nextLine();
            players[i] = new Player(name);
        }
        return players;
    }
}



