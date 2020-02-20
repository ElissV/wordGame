package com.example;

public class Score {

    private int score;
    private static final int[] pointsPerLetterForLevels;

    static {
        pointsPerLetterForLevels = new int[]{50, 100, 200};
    }

    Score() { score = 0; }

    private int calculateScore(String word) {
        int level = Main.getLevel();
        return word.length() * pointsPerLetterForLevels[level-1];
    }

    void setScore(String word) {
        score += calculateScore(word);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    int getScore() {
        return score;
    }

}