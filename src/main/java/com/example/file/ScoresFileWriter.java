package com.example.file;

import com.example.Main;
import com.example.score.TopScore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ScoresFileWriter {

    private static final File file;

    static {
        String filePath = "resources/scores.txt";
        file = new File(filePath);
    }

    public static void writeScores(TopScore topScore) {
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
