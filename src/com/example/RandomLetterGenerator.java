package com.example;

abstract class RandomLetterGenerator {

    private static final char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWYZ".toCharArray();
    private static char chosenLetter;

    static char generateLetter() {
        int arrayLen = letters.length - 1;
        int randomNumber = (int) (Math.random() * arrayLen);
        chosenLetter = letters[randomNumber];
        return chosenLetter;
    }

    static char getLetter() { return chosenLetter; }

}