package com.example;

public class Score {

    private static int score = 0;

    private int calculateScore(String word) {
        short scorePerLetter = 50;
        return scorePerLetter * word.length();
    }

    void clearScore() {
        score = 0;
        new WordCheck().clearGivenAnswers();
    }

    public int getScore() {
        return score;
    }

    void setScore(String word) {
        score += calculateScore(word);
    }

}