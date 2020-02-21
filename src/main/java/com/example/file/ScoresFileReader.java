package com.example.file;

import com.example.score.TopScore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScoresFileReader {

    private static final String filePath;

    static {
        filePath = "resources/scores.txt";
    }

    public static TopScore addPreviousScoresToList() {
        TopScore topScore = new TopScore();
        String fileContains = readFile();
        if (fileContains != null) {
            String[] strings = fileContains.split("/");
            for (String s : strings) {
                topScore.getTopScores().add(Integer.parseInt(s));
            }
        }
        return topScore;
    }

    private static String readFile() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (encoded.length == 0) return null;
        return new String(encoded, StandardCharsets.UTF_8);
    }

}