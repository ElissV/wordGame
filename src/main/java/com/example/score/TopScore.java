package com.example.score;

import com.example.gui.GameForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class TopScore {

    private static ArrayList<Integer> topScores;

    static {
        topScores = new ArrayList<>(6);
    }

    public static void showScores(GameForm gameForm) {
        String scores = "";
        for (int i = 0; i < 5; i++) {
            String currentScore = "0";
            if (topScores.size() > i) currentScore = String.valueOf(topScores.get(i));
            scores = scores.concat(i+1 + ". " + currentScore + "\n");
        }
        JOptionPane.showMessageDialog(gameForm.getJFrame(),
                scores, "Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    public void saveCurrentScore(int currentScore) {
        if (currentScore != 0) {
            topScores.add(currentScore);
            Collections.sort(topScores);
            Collections.reverse(topScores);
            int lastElementIndex = topScores.size() - 1;
            if (5 <= lastElementIndex) {
                topScores.remove(lastElementIndex);
            }
        }
    }

    public ArrayList<Integer> getTopScores() {
        return topScores;
    }

}