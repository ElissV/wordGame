package com.example;

import com.example.gui.GameForm;

import javax.swing.*;

public class Main {

    private static GameForm gameForm;

    public static void main(String[] args) {
        gameForm = new GameForm();
        TopScores.addPreviousScoresToList();
        showRules();
        startGame();
    }

    private static void showRules() {
        int timeTotal = Countdown.getTimeTotal();
        String rules = "Hi!\n The rules of the game are simple: you have to write as many words as you can" +
                " within " + timeTotal + " seconds.\n Your words have to begin with a certain letter.";
        JOptionPane.showMessageDialog(gameForm.getJFrame(), rules);
    }

    private static void startGame() {
        while (userWantsToStartGame())
        {
            clearWindowAfterPreviousGame();
            Countdown.startCountdown(gameForm);
            waitForGameToFinish();
            TopScores.saveCurrentScore();
            TopScores.writeScores();
        }
        System.exit(0);
    }

    private static boolean userWantsToStartGame() {
        char letter = RandomLetterGenerator.generateLetter();
        int choice = showOptionDialog(letter);
        if (choice == 2) TopScores.showScores(gameForm);

        if (choice == 1 || choice == 2) return userWantsToStartGame();
        return choice == 0;
    }

    private static int showOptionDialog(char letter) {
        String message = "Start the game? \nThe letter is " + letter;
        String[] options = {"Yes", "Change letter", "Show scores", "No"};
        return JOptionPane.showOptionDialog(gameForm.getJFrame(), message, "Word Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private static void clearWindowAfterPreviousGame() {
        WordCheck.clearScore();
        startOver();
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

    static void startOver() {
        gameForm.getScoreLabel().setText("Score: 0");
        gameForm.getTextArea().setText("");
        gameForm.getAnswerInputField().setText("");
    }
}