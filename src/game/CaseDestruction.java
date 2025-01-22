package game;

import static game.GameLogic.board;

public class CaseDestruction {
    // Détruit une case
    public static boolean destroyCase(String destruction) {
        int[] pos = parsePosition(destruction);
        if (pos == null || board[pos[0]][pos[1]] == 'X' || board[pos[0]][pos[1]] != '.') {
            System.out.println("Destruction invalide.");
            return false;
        }

        board[pos[0]][pos[1]] = 'X'; // Mettre un X sur la case
        System.out.println("Case détruite avec succès !");// Marquer la case comme détruite
        return true;
    }

    // Convertit une position en indices (ex: C5 -> [4][2])
    private static int[] parsePosition(String position) {
        if (position.length() != 2) return null;

        char col = position.charAt(0);
        char row = position.charAt(1);

        int x = row - '1'; // Ligne
        int y = col - 'A'; // Colonne

        if (x < 0 || x >= 10 || y < 0 || y >= 11) return null;
        return new int[]{x, y};
    }
}
