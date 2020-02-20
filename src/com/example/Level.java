package com.example;

class Level {

    private final static int MAX_LEVEL;
    private final static int[] requiredScoreToPassALevel;
    private int currentLevel;

    static {
        MAX_LEVEL = 3;
        requiredScoreToPassALevel = new int[]{2000, 3000, 4500};
    }

    Level() {
        currentLevel = 1;
    }

    boolean levelPassed(Score score) {
        return score.getScore() >= requiredScoreToPassALevel[currentLevel-1];
    }

    boolean gameIsFinished(Score score) {
        boolean currentLevelIsFinal = currentLevel == MAX_LEVEL;
        return currentLevelIsFinal && levelPassed(score);
    }

    @Override
    public String toString() {
        return String.valueOf(currentLevel);
    }

    void levelUp() {
        currentLevel++;
    }

    int getCurrentLevel() {
        return currentLevel;
    }

    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
