package com.example;

import com.example.gui.GameForm;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

abstract class TopScores {

    private static ArrayList<Integer> topScores;
    private static String filePath;
    private static File file;

    static {
        topScores = new ArrayList<>(6);
        filePath = "resources/scores.txt";
        file = new File(filePath);
    }

    static void showScores(GameForm gameForm) {
        String scores = "";
        for (int i = 0; i < 5; i++) {
            String currentScore = "0";
            if (topScores.size() > i) currentScore = String.valueOf(topScores.get(i));
            scores = scores.concat(i+1 + ". " + currentScore + "\n");
        }
        JOptionPane.showMessageDialog(gameForm.getJFrame(), scores, "Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    static void addPreviousScoresToList() {
        String fileContains = readFile();
        if (fileContains != null) {
            String[] strings = fileContains.split("/");
            for (String s : strings) {
                topScores.add(Integer.parseInt(s));
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

    static void saveCurrentScore() {
        int currentScore = WordCheck.getScore();
        if (currentScore == 0) {
            topScores.add(currentScore);
        } else {
            topScores.add(currentScore);
            Collections.sort(topScores);
            Collections.reverse(topScores);
            int lastElementIndex = topScores.size() - 1;
            if (topScores.size() > lastElementIndex) {
                topScores.remove(lastElementIndex);
            }
        }
    }

    static void writeScores() {
        try (Writer writer = new FileWriter(file, false)) {
            for (int i : topScores) {
                writer.write(String.valueOf(i + "/"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
