package com.example;

public class Score {

    private int score;
    private static final int[] POINTS_PER_LETTER;

    static {
        POINTS_PER_LETTER = new int[]{50, 100, 200, 600};
    }

    Score() { score = 0; }

    private int calculateScore(String word) {
        int level = Main.getLevel();
        return word.length() * POINTS_PER_LETTER[level-1];
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    void setScore(String word) {
        score += calculateScore(word);
    }

    int[] getPointsPerLetter() {
        return POINTS_PER_LETTER;
    }

    int getScore() {
        return score;
    }

}