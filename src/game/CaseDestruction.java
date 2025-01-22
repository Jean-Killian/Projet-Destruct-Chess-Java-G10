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
        int[] pos = parsePosition(destruction);
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
    private static int[] parsePosition(String position) {
        if (position.length() != 2) return null;

        char col = position.charAt(0);
        char row = position.charAt(1);

        int x = row - '1'; // Row
        int y = col - 'A'; // Column

        if (x < 0 || x >= 10 || y < 0 || y >= 11) return null;
        return new int[]{x, y};
    }
}
