package com.example.score;

import com.example.Main;

public class Score {

    private int score;
    private static final int[] POINTS_PER_LETTER;

    static {
        POINTS_PER_LETTER = new int[]{50, 100, 200, 600};
    }

    public Score() {
        score = 0;
    }

    private int calculateScore(String word) {
        int level = Main.getLevel();
        return word.length() * POINTS_PER_LETTER[level-1];
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    public void setScore(String word) {
        score += calculateScore(word);
    }

    public int[] getPointsPerLetter() {
        return POINTS_PER_LETTER;
    }

    public int getScore() {
        return score;
    }

}