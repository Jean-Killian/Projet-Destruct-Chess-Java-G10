package game;

import static game.GameLogic.board;

public class CaseDestruction {
    /**
     * Destroys a specified square on the board, if valid.
     * @param destruction The notation of the square to destroy (e.g., "C6")
     * @return true if the destruction is valid, otherwise false
     */
    // Destroy a square
    public static boolean destroyCase(String destruction) {
        byte[] pos = parsePosition(destruction);
        if (pos == null || board[pos[0]][pos[1]] == 'X' || board[pos[0]][pos[1]] != '·') {
            System.out.println("Destruction invalide.");
            return false;
        }

        board[pos[0]][pos[1]] = '■'; // Mettre un X sur la case
        System.out.println("Case détruite avec succès !");// Marquer la case comme détruite
        return true;
    }

    /**
     * Converts a position string (e.g., "C5") to board indices, or returns null if invalid.
     * @param position The position string
     * @return An array of two integers [row, column], or null if invalid
     */
    // Convert position to indices (e.g., C5 -> [4][2])
    private static byte[] parsePosition(String position) {
        // Vérifie si la position est valide en termes de longueur
        if (position.length() > 3 || position.length() < 2) return null;

        char col = position.charAt(0); // Récupère la colonne (premier caractère)
        String rowPart = position.substring(1); // Récupère la ligne de l'index 1 jusqu'à la fin
        byte row;

        try {
            row = (byte) (Integer.parseInt(rowPart) - 1); // Convertit en entier et ajuste pour un index 0-based
        } catch (NumberFormatException e) {
            return null; // Retourne null si la partie ligne n'est pas un entier valide
        }

        byte y = (byte) (col - 'A'); // Column

        if (row < 0 || row >= 10 || y < 0 || y >= 11) return null;
        return new byte[]{row, y};
    }
}
