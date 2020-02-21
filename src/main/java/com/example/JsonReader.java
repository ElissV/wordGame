package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class JsonReader {

    static boolean wordExists(String word) {
        HttpURLConnection connection = establishConnection(word);
        if (connectionSuccess(connection)) {
            String allInput = readWebPageContent(connection);
            return parseToJsonArrayAndGetValue(allInput);
        }
        return false;
    }

    private static HttpURLConnection establishConnection(String word) {
        String apiKey = "dict.1.1.20200220T183443Z.d2af56cbde105543.51d80441b631ff33a4e78c69dce43f0218f13264";
        String stringURL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key="
                + apiKey + "&lang=en-ru&text=" + word;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(stringURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
        } catch (IOException e) {
            Main.showErrorTitleAndMessage(e);
        }
        return connection;
    }

    private static boolean connectionSuccess(HttpURLConnection connection) {
        try {
            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            Main.showErrorTitleAndMessage(e);
        }
        return false;
    }

    private static String readWebPageContent(HttpURLConnection connection) {
        BufferedReader br;
        StringBuilder allLines = null;
        try {
            br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            allLines = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                allLines.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (allLines == null) return null;
        return allLines.toString();
    }

    private static boolean parseToJsonArrayAndGetValue(String allInput) {
        Object obj = parseToObject(allInput);
        JSONObject jsonObject = (JSONObject) obj;
        String value = null;
        if (jsonObject != null) {
            JSONArray jsonArray = (JSONArray) jsonObject.get("def");
            value = getValue(jsonArray);
        }
        return value != null;
    }

    private static Object parseToObject(String allInput) {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(allInput);
            return obj;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getValue(JSONArray jsonArray) {
        JSONObject jsonObject = null;
        if (!jsonArray.isEmpty())
            jsonObject = (JSONObject) jsonArray.get(0);
        if (jsonObject != null) {
            return (String) jsonObject.get("pos");
        }
        return null;
    }

}
