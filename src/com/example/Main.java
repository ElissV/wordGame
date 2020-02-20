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
        while (userWantsToStartGame())
        {
            System.out.println(wordCheck.getGivenAnswers() + "\n" + level + "\n" + score);
            Countdown.startCountdown(gameForm);
            waitForGameToFinish();
            checkResultAndSaveIfFinished();
        }
        System.exit(0);
    }

    private static void showRules() {
        int timeTotal = Countdown.getTimeTotal();
        String rules = "Hi!\n The rules of the game are simple: you have to write as many words as you can within "
                + timeTotal + " seconds.\n Your words have to begin with a certain letter(s).";
        JOptionPane.showMessageDialog(gameForm.getJFrame(), rules);
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
        if (level.levelPassed(score)) {
            level.levelUp();
            gameForm.getTextArea().setText("");
            gameForm.getAnswerInputField().setText("");
            wordCheck = new WordCheck();
        }
        if (level.gameIsFinished(score) || !level.levelPassed(score)) {
            topScore.saveCurrentScore();
            ScoreWriter.writeScores(topScore);
            startOver();
        }
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