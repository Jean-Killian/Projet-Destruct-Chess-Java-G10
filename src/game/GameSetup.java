package game;
import java.util.Scanner;

public class GameSetup {

    private static String[] players = new String[4];

    // Méthode pour demander le nombre de joueurs et leurs pseudos
    public static int[] getPlayersInfo() {
        Scanner scanner = new Scanner(System.in);

        // Demander combien de joueurs vont jouer
        System.out.println("\nCombien de joueurs vont jouer ? (2, 3 ou 4)");
        int numberOfPlayers = scanner.nextInt();

        // Validation du nombre de joueurs
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("Nombre de joueurs invalide. Veuillez entrer un nombre entre 2 et 4.");
            numberOfPlayers = scanner.nextInt();
        }

        // Demander les pseudos des joueurs
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Entrez le pseudo du Joueur " + (i + 1) + ": ");
            players[i] = scanner.next();
        }

        return new int[]{numberOfPlayers};
    }
}
