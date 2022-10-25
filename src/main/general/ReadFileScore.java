package main.general;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static main.settings.StatusGame.isSurvival;

public class ReadFileScore {
    public void addScore(int score) throws IOException {
        String path;
        if (isSurvival == false) {
            path = "res/levels/HighScoreLevel.txt";
        } else {
            path = "res/levelTraining/HighScoreSurvival.txt";
        }

        // open file writer with write from start

        ArrayList<Integer> listScore = new ArrayList<>();
        listScore = readScore();
        listScore.add(score);
        Collections.sort(listScore, Collections.reverseOrder());

        FileWriter fileWriter = new FileWriter(path, false);
        for (int i = 0; i < 5 && i < listScore.size(); i++) {
            fileWriter.write(listScore.get(i) + "\n");
        }

        fileWriter.close();
    }

    public ArrayList<Integer> readScore() {
        String path;
        if (isSurvival == false) {
            path = "res/levels/HighScoreLevel.txt";
        } else {
            path = "res/levelTraining/HighScoreSurvival.txt";
        }
        ArrayList<Integer> score = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            try {
                java.util.Scanner scanner = new java.util.Scanner(file);
                while (scanner.hasNextInt()) {
                    score.add(scanner.nextInt());
                }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return score;
    }
}
