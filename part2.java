import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
            String meaningfulName = makeMeaningful(line[2]);
            stopNames.add(meaningfulName);
        }
        br.close();
        return stopNames;
    }

    public static void main(String[] args) throws IOException {
        File stops = new File("inputs/stops.txt");
        String[] stops_column_names = getColumnNames(stops);

        ArrayList<String> stopNames = getStopNames(stops);

        // System.out.println(Arrays.toString(stops_column_names));

        for (String x : stopNames) {
        System.out.println(x);
        }

    }
}
