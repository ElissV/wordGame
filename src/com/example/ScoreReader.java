package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class ScoreReader {

    private static final String filePath;

    static {
        filePath = "resources/scores.txt";
    }

    static void addPreviousScoresToList() {
        String fileContains = readFile();
        if (fileContains != null) {
            String[] strings = fileContains.split("/");
            for (String s : strings) {
                TopScore topScore = new TopScore();
                topScore.getTopScores().add(Integer.parseInt(s));
            }
        }
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