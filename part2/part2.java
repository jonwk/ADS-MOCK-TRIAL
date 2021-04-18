import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class part2 {
    public static String[] getColumnNames(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            br.close();
            return line;
        }
        br.close();
        return null;
    }

    // In order for this to provide meaningful search functionality please move
    // keywords flagstop, wb, nb, sb, eb from start of the names to the end of the
    // names of the stops when reading the file into a TST (eg “WB HASTINGS ST FS
    // HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)
    public static String makeMeaningful(String stopName) {
        String temp = stopName.substring(2, 3);
        if (temp.equals(" ")) {
            String lastPart = stopName.substring(3);
            String firstPart = stopName.substring(0, 2);
            String meaningfulStr = lastPart.concat(" ").concat(firstPart);
            return meaningfulStr;
        } else
            return stopName;

    }

    public static ArrayList<String> getStopNames(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        ArrayList<String> stopNames = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (!line[2].equals("stop_name")) {
                String meaningfulName = makeMeaningful(line[2]);
                stopNames.add(meaningfulName);
            }
        }
        br.close();
        return stopNames;
    }

    public static void printDuplicateStations(ArrayList<String> stopNames) {
        ArrayList<String> stopNamesUniques = new ArrayList<String>();
        int duplicateCount = 0;
        System.out.println("------- Duplicate Stop Names -------");
        for (String x : stopNames) {
            if (stopNamesUniques.contains(x)) {
                duplicateCount++;
                System.out.println(duplicateCount + " " + x);
            } else
                stopNamesUniques.add(x);
        }

        System.out.println("Stops count - " + stopNames.size());
        System.out.println("Unique Stops count - " + stopNamesUniques.size());
    }

    public static Map<String, ArrayList<String>> createNameDetailsMap(File filename) throws IOException {

        int indexOfStopName = 2;
        Map<String, ArrayList<String>> Time_Line = new TreeMap<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;

        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (!line[indexOfStopName].equals("stop_name")) {
                String meaningfulName = makeMeaningful(line[indexOfStopName]);
                Time_Line.computeIfAbsent(meaningfulName, k -> new ArrayList<>()).add(st);
                // stopNames.add(meaningfulName);
            }
        }
        br.close();
        return Time_Line;
    }

    public static void main(String[] args) throws IOException {
        File stops = new File("inputs/stops.txt");
        String[] stops_column_names = getColumnNames(stops);

        ArrayList<String> stopNames = getStopNames(stops);

        // printDuplicateStations(stopNames);

        // System.out.println(Arrays.toString(stops_column_names));

        Map<String, ArrayList<String>> stopDetails = createNameDetailsMap(stops);

        // Set<String> uniqueStops = new HashSet<String>(stopNames);
        // System.out.println("Unique Stops count: " + uniqueStops.size());

    }
}
