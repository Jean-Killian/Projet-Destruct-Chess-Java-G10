package game;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MinuteurJeu {
    public static void main(String[] args) {
        boolean joueur1Tour = true; // Indique si c'est le tour du joueur 1
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (joueur1Tour) {
                    System.out.println("C'est le tour du Joueur 1.");
                } else {
                    System.out.println("C'est le tour de l'Adversaire.");
                }

                System.out.println("Le minuteur démarre pour 10 secondes...");
                lancerMinuteur(10); // Lance un minuteur de 10 secondes

                System.out.println("Appuyez sur Entrée pour passer au tour suivant.");
                scanner.nextLine(); // Attend une action pour passer au tour suivant

                // Changer de joueur
                joueur1Tour = !joueur1Tour;
            }
        }
    }

    public static void lancerMinuteur(int secondes) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int tempsRestant = secondes;

            @Override
            public void run() {
                if (tempsRestant > 0) {
                    System.out.println("Temps restant : " + tempsRestant + " secondes");
                    tempsRestant--;
                } else {
                    System.out.println("Temps écoulé !");
                    timer.cancel();
                }
            }
        };

        // Planifie la tâche pour s'exécuter toutes les secondes
        timer.scheduleAtFixedRate(task, 0, 1000);

    }
    

}