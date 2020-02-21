package com.example;

import java.util.ArrayList;
import java.util.List;

public class WordCheck {

    private List<String> givenAnswers;

    WordCheck() {
        givenAnswers = new ArrayList<>();
    }

    public void checkForCorrectAnswer(String word) {
        if (word.isEmpty() || word.length() == 1)
            return;
        if (wordStartsWithRightChar(word)) {
            if (JsonReader.wordExists(word)) {
                if (!(givenAnswers.contains(word))) {
                    givenAnswers.add(word);
                    Score score = Main.getScore();
                    score.setScore(word);
                }
            }
        }
    }

    private boolean wordStartsWithRightChar(String word) {
        String inputFistLetters = getFirstChars(word);
        String chosenLetters = Main.getChosenLetters();
        return inputFistLetters.equals(chosenLetters);
    }

    private String getFirstChars(String word) {
        int level = Main.getLevel();
        return word.substring(0, level).toUpperCase();
    }

}