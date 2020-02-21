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

    static void writeScores(TopScore topScore) {
        List<Integer> topScores = topScore.getTopScores();
        try (Writer writer = new FileWriter(file, false)) {
            for (int i : topScores) {
                writer.write(String.valueOf(i + "/"));
            }
        } catch (IOException e) {
            Main.showErrorTitleAndMessage(e);
        }
    }

}
