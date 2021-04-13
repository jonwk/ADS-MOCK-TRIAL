import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * firstfile
 */
public class firstfile {

    public static String[] getColumnNames(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            // br.close();
            return line;
        }
        br.close();
        return null;
    }

    public static int getLinesCount(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        int count = 0;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            count++;
        }
        br.close();
        return count;
    }

    public static boolean isTimeValid(String time) {
        final int MAX_HOURS = 24;
        final int MAX_MINUTES = 59;
        final int MAX_SECONDS = 59;

        final int MIN_HOURS = 00;
        final int MIN_MINUTES = 00;
        final int MIN_SECONDS = 00;

        // System.out.println("\n Input time - "+time);
        String time_without_space = time.replaceAll("\\s", "");
        // System.out.println("\n time without space - "+time_without_space);

        String[] individualParts = time_without_space.split(":");
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        try {
            hours = Integer.parseInt(individualParts[0]);
            minutes = Integer.parseInt(individualParts[1]);
            seconds = Integer.parseInt(individualParts[2]);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        // System.out.println(" hours - " + hours +" minutes - "+ minutes+" Seconds -
        // "+seconds);
        if (hours > MAX_HOURS || minutes > MAX_MINUTES || seconds > MAX_SECONDS) {
            // if (hours < MIN_HOURS || minutes < MIN_MINUTES || seconds < MIN_SECONDS)
            // System.out.println("invalid time - " + time);
            return false;
        } else
            return true;
    }

    public static ArrayList<String> getValidTimes(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        ArrayList<String> validTimes = new ArrayList<String>();
        ArrayList<String> invalidTimes = new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            // System.out.println(Arrays.toString(line));
            // columns in stop_times.txt
            // [trip_id, arrival_time, departure_time, stop_id, stop_sequence,
            // stop_headsign, pickup_type, drop_off_type, shape_dist_traveled]
            String arrival_time = line[1];
            String departure_time = line[2];
            if (isTimeValid(arrival_time) && isTimeValid(departure_time)) {
                validTimes.add(Arrays.toString(line));
            } else {
                invalidTimes.add(Arrays.toString(line));
            }
        }
        // System.out.println("Valid times - " + validTimes.size() + " Invalid times - "
        // + invalidTimes.size()
        // + " Number of lines - " + lineCount);
        br.close();
        return validTimes;
    }

    public static void main(String[] args) throws IOException {
        // File stops = new File("inputs/stops.txt");
        // File transfers = new File("inputs/transfers.txt");
        // File stop_times = new File("inputs/stop_times.txt");

        // int stops_count = getLinesCount(stops);
        // int stops_times_count = getLinesCount(stop_times);
        // int transfers_count = getLinesCount(transfers);

        // System.out.println("Lines in stops.txt - " + stops_count);

        // System.out.println("Lines in transfers.txt - " + transfers_count);

        // System.out.println("Lines in stop_times.txt - " + stops_times_count);

        // String[] stops_columns = getColumnNames(stops);
        // String[] transfers_columns = getColumnNames(transfers);
        // String[] stops_times_columns = getColumnNames(stop_times);

        // System.out.println("Column Names in stops.txt - " +
        // Arrays.toString(stops_columns));

        // System.out.println("Column Names in transfers.txt - " +
        // Arrays.toString(transfers_columns));

        // System.out.println("Column Names in stop_times.txt - " +
        // Arrays.toString(stops_times_columns));

        // String time1 = " 5:25:00";
        // String time2 = "25:20:00";

        // boolean bool1 = isTimeValid(time1);
        // boolean bool2 = isTimeValid(time2);

        File stop_times = new File("inputs/stop_times.txt");
        ArrayList<String> validTimes = getValidTimes(stop_times);

        System.out.println("FirstLine " + validTimes.get(0));
        System.out.println("SecondLine " + validTimes.get(1));

        System.out.println("valid elements - " + validTimes.size());
    }
}