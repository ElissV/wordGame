package com.example;

class Level {

    private final static int MAX_LEVEL;
    private final static int[] requiredScoreToPassALevel;
    private int currentLevel;

    static {
        MAX_LEVEL = 4;
        requiredScoreToPassALevel = new int[]{4000, 8000, 16000, 30000};
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

    int[] getRequiredScoreToPassLevel() {
        return requiredScoreToPassALevel;
    }
}
