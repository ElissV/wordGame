package com.example;

import com.example.gui.GameForm;

import javax.swing.*;


class GameLauncher {

    private GameForm gameForm;

    GameLauncher() {
        gameForm = new GameForm();
        TopScores.addPreviousScoresToList();
        showRules();
    }

    private void showRules() {
        int timeTotal = Countdown.getTimeTotal();
        String rules = "Hi!\n The rules of the game are simple: you have to write as many words as you can" +
                " within " + timeTotal + " seconds.\n Your words have to begin with a certain letter.";
        JOptionPane.showMessageDialog(gameForm.getJFrame(), rules);
    }

    void startGame() {
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

    private boolean userWantsToStartGame() {
        char letter = RandomLetterGenerator.generateLetter();
        int choice = showOptionDialog(letter);
        if (choice == 2) TopScores.showScores(gameForm);

        if (choice == 1 || choice == 2) return userWantsToStartGame();
        return choice == 0;
    }

    private int showOptionDialog(char letter) {
        String message = "Start the game? \nThe letter is " + letter;
        String[] options = {"Yes", "Change letter", "Show scores", "No"};
        return JOptionPane.showOptionDialog(gameForm.getJFrame(), message, "Word Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private void clearWindowAfterPreviousGame() {
        WordCheck.clearScore();
        gameForm.startOver();
    }

    private void waitForGameToFinish() {
        int timeToWait = Countdown.getTimeTotal() * 1000;
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(gameForm.getJFrame(), e,
                    "Error occurred", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}