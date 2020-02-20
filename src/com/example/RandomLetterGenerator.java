package com.example;

import java.util.Arrays;

class RandomLetterGenerator {

    private static final String[] letters;
    private static final String[] letterCombinationLvlTwo;
    private static final String[] letterCombinationLvlThree;
    private static final String[][] lettersArrays;
    private String chosenLetter;

    static {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
        letters = alphabet.split("");
        letterCombinationLvlTwo = new String[]{"FR", "AS", "FO", "NU",
                "GO", "DI", "CO", "CL", "BL", "SE", "RO", "PR",
                "DO", "AN", "RU", "TA", "MO", "EA", "OR", "GE"};
        letterCombinationLvlThree = new String[]{"INT", "ACC", "DIS",
                "IMP", "NUM", "MIN", "IRR", "INA", "QUE", "APP",
                "ILL", "MIS", "SUB", "PRE"};
        lettersArrays = new String[][]{letters,
                letterCombinationLvlTwo, letterCombinationLvlThree};
    }

    String generateLetters(Level level) {
        int currentLevel = level.getCurrentLevel() - 1;
        int arrayLen = lettersArrays[currentLevel].length - 1;
        int randomNumber = (int) (Math.random() * arrayLen);
        chosenLetter = lettersArrays[currentLevel][randomNumber];
        return chosenLetter;
    }

    String getLetter() {
        return chosenLetter;
    }

}