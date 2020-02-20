package com.example;

import java.util.Arrays;

class RandomLetterGenerator {

    private static final String[] letters;
    private static final String[] letterCombinationLvlTwo;
    private static final String[] letterCombinationLvlThree;
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
    }

    String generateLetter() {
        int arrayLen = letters.length - 1;
        int randomNumber = (int) (Math.random() * arrayLen);
        chosenLetter = letters[randomNumber];
        return chosenLetter;
    }

    String getLetter() {
        return chosenLetter;
    }

}