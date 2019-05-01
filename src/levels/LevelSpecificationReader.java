package levels;


import interfaces.LevelInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * .
 * Author: Eyal Styskin
 * Class Operation : reading the level definitions files
 */
public class LevelSpecificationReader {

    /**
     * .
     * reads all the file and seperates it to array of lines.
     *
     * @param reader Reader  -connected to file
     * @return - List of LevelInformations
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<SpecificLevel> specificLevels = this.readLines(bufferedReader);
        for (int i = 0; i < specificLevels.size(); ++i) {
            try {
                levels.add(specificLevels.get(i).getLevel());
            } catch (NullPointerException e) {
                System.out.println("cant add level");
            }
        }
        return levels;
    }

    /**
     * .
     * reading all the lines of the file and seperates it to different levels
     *
     * @param bufferedReader bufferedReader - reading full line
     * @return - list of levels
     */
    public ArrayList<SpecificLevel> readLines(BufferedReader bufferedReader) {
        ArrayList<SpecificLevel> levels = new ArrayList<SpecificLevel>();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("START_LEVEL")) {
                    ArrayList<String> lines = new ArrayList<String>();
                    while (!line.equals("END_LEVEL")) {
                        line = bufferedReader.readLine();
                        try {
                            lines.add(line);
                        } catch (NullPointerException e) {
                            System.out.println("cant add line");
                        }
                    }
                    levels.add(new SpecificLevel(lines));
                }
            } catch (IOException e) {
                System.out.println("cant read from files-SpecificationReader");
            }
        }
        return levels;
    }
}



