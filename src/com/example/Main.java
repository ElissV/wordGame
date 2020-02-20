package com.example;

import com.example.gui.GameForm;

import javax.swing.*;

public class Main {

    private static GameForm gameForm;
    private static RandomLetterGenerator letterGenerator;
    private static TopScore topScore;
    private static WordCheck wordCheck;
    private static Level level;
    private static Score score;

    static {
        gameForm = new GameForm();
        letterGenerator = new RandomLetterGenerator();
        level = new Level();
        score = new Score();
        wordCheck = new WordCheck();
    }

    public static void main(String[] args) {
        topScore = ScoreReader.addPreviousScoresToList();
        startGame();
    }

    private static void startGame() {
        showRules();
        while (userWantsToStartGame()) {
            Countdown.startCountdown(gameForm);
            waitForGameToFinish();
            checkResultAndSaveIfFinished();
        }
        System.exit(0);
    }

    private static void showRules() {
        int timeTotal = Countdown.getTimeTotal();
        String rules = "Hi! \nThe rules of the game are simple: you have to write " +
                "as many words as you can (and as long as you can) within " + timeTotal +
                " seconds. \nYour words have to begin with a certain letter(s).\n\n";
        rules = rules + getRequiredScoresForLevelPass();
        JOptionPane.showMessageDialog(gameForm.getJFrame(), rules);
    }

    private static String getRequiredScoresForLevelPass() {
        int[] requiredScores = level.getRequiredScoreToPassLevel();
        int[] pointsPerLetter = score.getPointsPerLetter();
        StringBuilder s = new StringBuilder("You have to gain: \n");
        for (int lvl=0; lvl<requiredScores.length; lvl++) {
            s.append(requiredScores[lvl])
                    .append(" points for level ")
                    .append(lvl+1)
                    .append(" (you earn ")
                    .append(pointsPerLetter[lvl])
                    .append(" per letter)\n");
        }
        return s.toString();
    }

    private static boolean userWantsToStartGame() {
        int choice = generateLetterAndShowMenu();
        if (choice == 2)
            TopScore.showScores(gameForm);
        if (choice == 1 || choice == 2)
            return userWantsToStartGame();

        return choice == 0;
    }

    private static void waitForGameToFinish() {
        int timeToWait = Countdown.getTimeTotal() * 1000;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(gameForm.getJFrame(), e,
                    "Error occurred", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void checkResultAndSaveIfFinished() {
        String message = "";
        if (level.gameIsFinished(score)) {
            gameFinished();
            message = "Congrats!" + getResult();
        }
        if (!level.levelPassed(score)) {
            gameFinished();
            message = "You lose!" + getResult();
        }
        if (level.levelPassed(score)) {
            prepareForNextLevel();
            message = "Cool! " + getResult();
        }
        JOptionPane.showMessageDialog(gameForm.getJFrame(), message);
    }

    private static void gameFinished() {
        topScore.saveCurrentScore();
        ScoreWriter.writeScores(topScore);
        startOver();
    }

    private static String getResult() {
        int lvl = level.getCurrentLevel();
        int requiredScore = level.getRequiredScoreToPassLevel()[lvl-1];
        return "\n You got " + score + " out of " + requiredScore;
    }

    private static void prepareForNextLevel() {
        level.levelUp();
        gameForm.getTextArea().setText("");
        gameForm.getAnswerInputField().setText("");
        wordCheck = new WordCheck();
    }

    private static int generateLetterAndShowMenu() {
        String letters = letterGenerator.generateLetters(level);
        String message = "Level " + level +
                "\nStart the game? Words should start with " + letters;
        String[] options = {"Yes", "Change letter(s)", "Show scores", "No"};
        return JOptionPane.showOptionDialog(gameForm.getJFrame(), message, "Word Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private static void startOver() {
        wordCheck = new WordCheck();
        level = new Level();
        score = new Score();
        clearFields();
    }

    private static void clearFields() {
        gameForm.getScoreLabel().setText("Score: 0");
        gameForm.getTextArea().setText("");
        gameForm.getAnswerInputField().setText("");
    }

    public static Score getScore() {
        return score;
    }

    static int getLevel() {
        return level.getCurrentLevel();
    }

    public static WordCheck getWordCheck() {
        return wordCheck;
    }

    static String getChosenLetters() {
        return letterGenerator.getLetter();
    }
}