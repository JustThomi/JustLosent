package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoad {

    private static List<String> scoreList = new ArrayList<String>();

    public SaveLoad() {
    }

    /**
     * @return simple list of scores
     */
    public static String[] getScores() {
        String[] simpleList = new String[scoreList.size()];
        scoreList.toArray(simpleList);

        return simpleList;
    }

    /**
     * Reads csv file and saves score data
     * 
     * @throws Exception
     */
    public static void readData() throws Exception {
        Scanner scanner = new Scanner(new File("src/data/scores.csv"));
        scanner.useDelimiter(",");

        scoreList.clear();
        while (scanner.hasNext()) {
            scoreList.add(scanner.next() + ": " + scanner.next());
        }

        scanner.close();
    }

    /**
     * Saves score data to csv file
     * 
     * @param name
     * @param score
     * @throws Exception
     */
    public static void writeData(String name, int score) throws Exception {
        File scores = new File("src/data/scores.csv");
        FileWriter writer = new FileWriter(scores, true);
        BufferedWriter out = new BufferedWriter(writer);

        out.write(name + ", " + Integer.toString(score) + ",");
        out.newLine();
        out.close();
    }
}
