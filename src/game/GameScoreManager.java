package game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;

public class GameScoreManager {
    private static final String SCORES_FILE = "scores.json";
    private static final String HASH_FILE = "scores.hash";
    private Map<String, Integer> scores = new HashMap<>();
    private final Gson gson = new Gson();

    public GameScoreManager() {
        loadScores();
    }

    // Load scores from file
    private void loadScores() {
        try {
            if (Files.exists(Path.of(SCORES_FILE))) {
                String json = Files.readString(Path.of(SCORES_FILE));
                scores = gson.fromJson(json, new TypeToken<Map<String, Integer>>() {}.getType());
            }
        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }
    }

    // Save scores and generate hash
    private void saveScores() {
        try {
            String json = gson.toJson(scores);
            Files.writeString(Path.of(SCORES_FILE), json);

            String hash = generateHash(json);
            Files.writeString(Path.of(HASH_FILE), hash);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }

    // Generate SHA-256 hash
    private String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    // Update scores based on rankings
    public void updateScores(List<String> usernames) {
        int[] scoreChanges;
        switch (usernames.size()) {
            case 2:
                scoreChanges = new int[]{5, -2};
                break;
            case 3:
                scoreChanges = new int[]{5, 0, -2};
                break;
            case 4:
                scoreChanges = new int[]{5, 2, -1, -2};
                break;
            default:
                throw new IllegalArgumentException("Invalid number of players: " + usernames.size());
        }

        for (int i = 0; i < usernames.size(); i++) {
            String user = usernames.get(i);
            scores.put(user, scores.getOrDefault(user, 0) + scoreChanges[i]);
        }
        saveScores();
    }

    // Validate the hash
    public boolean validateScores() {
        try {
            String currentJson = gson.toJson(scores);
            String currentHash = generateHash(currentJson);

            if (Files.exists(Path.of(HASH_FILE))) {
                String savedHash = Files.readString(Path.of(HASH_FILE));
                return currentHash.equals(savedHash);
            }
        } catch (IOException e) {
            System.err.println("Error validating scores: " + e.getMessage());
        }
        return false;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }
}
