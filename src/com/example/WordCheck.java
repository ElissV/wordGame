package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordCheck {

    private static List<String> givenAnswers;
    private static Score score;

    static {
        givenAnswers = new ArrayList<>();
        score = new Score();
    }

    public void checkForCorrectAnswer(String word)
    {
        if (word.isEmpty() || word.length() == 1)
            return;
        if (wordStartsWithRightChar(word)) {
            if (wordExists(word)) {
                if (!(givenAnswers.contains(word))) {
                    givenAnswers.add(word);
                    score.setScore(word);
                }
            }
        }
    }

    private boolean wordStartsWithRightChar(String word) {
        char firstLetter = Character.toUpperCase(word.charAt(0));
        char chosenChar = RandomLetterGenerator.getLetter();
        return firstLetter == chosenChar;
    }

    private boolean wordExists(String word) {
        int response = 0;
        try {
            String stringURL = "https://od-api.oxforddictionaries.com/api/v2/entries/en-us/" + word;
            String appID = "bba45605";
            String appKey = "7ce5f36c0dcc3c2c65a78bcde080be5d";

            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("app_id", appID);
            connection.setRequestProperty("app_key", appKey);
            response = connection.getResponseCode();

        } catch (IOException e) {
            System.out.println("An issue occurred: " + e);
        }
        return response == 200;
    }

    void clearGivenAnswers() {
        givenAnswers.clear();
    }

}