package game;

import java.util.Scanner;

public class GameLogic {

    public static char[][] board; // Plateau de jeu
    private boolean[][] destroyed; // Cases détruites
    private int currentPlayer; // Indice du joueur actuel

    // Constructeur : initialise le plateau et les cases détruites
    public GameLogic(Player[] players) {
        this.board = new char[10][11]; // 10 lignes x 11 colonnes
        this.destroyed = new boolean[10][11]; // Toutes les cases sont intactes
        initializeBoard();
        this.currentPlayer = 1; // Le joueur 1 commence
    }

    // Initialise le plateau avec des cases vides et les joueurs au centre
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.'; // Case vide
            }
        }
        board[5][2] = '1'; // Position initiale du joueur 1
        board[5][3] = '2'; // Position initiale du joueur 2
    }

    // Affiche l'état actuel du plateau
    public static void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Start the game
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard();
            System.out.println("C'est au tour du joueur " + currentPlayer);
            // Minuteur
                long startTime = System.currentTimeMillis();
                long endTime = startTime + 15000; // 15 seconds in milliseconds

                while (System.currentTimeMillis() < endTime) {
                    // Check if the player has made a move within the time limit
                    if (scanner.hasNextLine()) {
                        break;
                    }
                }

                if (System.currentTimeMillis() >= endTime) {
                    System.out.println("Temps écoulé! Le tour passe au joueur suivant.");
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    isGameOver();
                }
            // Déplacement
            System.out.print("Déplacez votre pion avec Z (haut), Q (gauche), S (bas), D (droite) : ");
            String direction = scanner.nextLine();
            if (movePlayer(direction)) {
                // Destruction
                System.out.print("Entrez une case à détruire (ex: C6) : ");
                String destruction = scanner.nextLine();
                if (destroyCase(destruction)) {
                    // Passer au joueur suivant
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            }
        }
        scanner.close();
    }

    // Déplace un joueur
    private boolean movePlayer(String direction) {
        // Trouver la position actuelle du joueur
        int currentX = -1, currentY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == (char) ('0' + currentPlayer)) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }

        }

        // Déterminer la nouvelle position en fonction de la direction
        int newX = currentX, newY = currentY;
        switch (direction.toUpperCase()) {
            case "W": // Haut
                newX -= 1;
                break;
            case "S": // Bas
                newX += 1;
                break;
            case "A": // Gauche
                newY -= 1;
                break;
            case "D": // Droite
                newY += 1;
                break;
            default:
                System.out.println("Direction invalide. Utilisez W (haut), A (gauche), S (bas), D (droite).");
                return false;
        }

        // Vérifier que la nouvelle position est valide
        if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
            System.out.println("Déplacement hors limites.");
            return false;
        }
        if (destroyed[newX][newY] || board[newX][newY] != '.') {
            System.out.println("Déplacement invalide. La case est détruite ou occupée.");
            return false;
        }

        // Déplacer le joueur
        board[currentX][currentY] = '.'; // Effacer l'ancienne position
        board[newX][newY] = (char) ('0' + currentPlayer); // Mettre à jour la nouvelle position
        return true;
    }

    /*
     * pseudo code() {
     * if the player encounters an empty square
     * then
     * the player moves
     * but
     * if the square is destroyed or occupied by a player
     * the player will be blocked and therefore cannot move
     * if one player don't move
     * game is end
     * }
     */
    public boolean isMovePossible(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (destroyed[x][y] || board[x][y] != '.') {
            return false;
        }
        return true;
    }

    // Check if the current player can move
    public boolean canPlayerMove() {
        int currentX = -1, currentY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {


                /*
                 * je vérifie si la case n'est pas cassé
                 * alors avancer (bas ; gauche ; droite ; haut)
                 * je vérifie si la case est occupé ou pas
                 * sinon se déplace pas et reste au même endroit
                 */


                if (board[i][j] == (char) ('0' + currentPlayer)) {
                    currentX = i;
                    currentY = j;
                    break;
                }
            }
        }

        // Vérifie toutes les possibiités 

            if(isMovePossible(currentX - 1, currentY)) {
                return true;
            } else if (isMovePossible(currentX + 1, currentY)) {
                return true;
            } else if (isMovePossible(currentX, currentY - 1)) {
                return true;
            } else if (isMovePossible(currentX, currentY + 1)) {
                return true; 
            } else {
                return false;
            }
        }
            //ancien code
            // /*return isMovePossible(currentX - 1, currentY)| | // Up
           //     isMovePossible(currentX + 1, currentY) || // Down
            //     isMovePossible(currentX, currentY - 1) || // Left
            //     isMovePossible(currentX, currentY + 1); // Right
            // }*/

    // Check if the game should end
    public boolean isGameOver() {
        return !canPlayerMove();
    }

    // Détruit une case
    private boolean destroyCase(String destruction) {
        int[] pos = parsePosition(destruction);
        if (pos == null || destroyed[pos[0]][pos[1]] || board[pos[0]][pos[1]] != '.') {
            System.out.println("Destruction invalide.");
            return false;
        }

        destroyed[pos[0]][pos[1]] = true; // Marquer la case comme détruite
        return true;
    }

    // Convertit une position en indices (ex: C5 -> [4][2])
    private int[] parsePosition(String position) {
        if (position.length() != 2)
            return null;

        char col = position.charAt(0);
        char row = position.charAt(1);

        int x = row - '1'; // Ligne
        int y = col - 'A'; // Colonne

        if (x < 0 || x >= 10 || y < 0 || y >= 11)
            return null;
        return new int[] { x, y };
    }
}