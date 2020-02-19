package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

class ScoreWriter {

    private static final File file;

    static {
        String filePath = "resources/scores.txt";
        file = new File(filePath);
    }

    static void writeScores() {
        List<Integer> topScores = new TopScore().getTopScores();
        try (Writer writer = new FileWriter(file, false)) {
            for (int i : topScores) {
                writer.write(String.valueOf(i + "/"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
