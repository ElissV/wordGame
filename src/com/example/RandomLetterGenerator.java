package com.example;

class RandomLetterGenerator {

    private static final char[] letters;
    private static final String[] letterCombinationLvlTwo;
    private static final String[] letterCombinationLvlThree;
    private char chosenLetter;

    static {
        letters = "ABCDEFGHIJKLMNOPQRSTUVWYZ".toCharArray();
        letterCombinationLvlTwo = new String[]{"FR", "AS", "FO", "NU",
                "GO", "DI", "CO", "CL", "BL", "SE", "RO", "PR",
                "DO", "AN", "RU", "TA", "MO", "EA", "OR", "GE"};
        letterCombinationLvlThree = new String[]{"INT", "ACC", "DIS",
                "IMP", "NUM", "MIN", "IRR", "INA", "QUE", "APP",
                "ILL", "MIS", "SUB", "PRE"};
    }

    char generateLetter() {
        int arrayLen = letters.length - 1;
        int randomNumber = (int) (Math.random() * arrayLen);
        chosenLetter = letters[randomNumber];
        return chosenLetter;
    }

    char getLetter() {
        return chosenLetter;
    }

}