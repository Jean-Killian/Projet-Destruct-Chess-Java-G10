package ui;

public class Rules {
    // Affiche les règles du jeu
    public static void showRules() {
        System.out.println("\n===== Règles du jeu =====");
        System.out.println("1. Chaque joueur peut déplacer son pion d'une case (verticalement ou horizontalement).");
        System.out.println("2. Après un déplacement, une case du plateau doit être détruite.");
        System.out.println("3. Le joueur bloqué est déclaré perdant.");
        System.out.println("4. Vous ne pouvez pas occuper ou détruire une case déjà occupée.");
        System.out.println("5. Le dernier joueur à pouvoir se déplacer gagne.");
        System.out.println("=========================\n");
    }
}
