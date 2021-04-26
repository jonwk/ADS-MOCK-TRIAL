import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class part1 {

    // * Finding shortest paths between 2 bus stops (as input by the user),
    // returning the list of stops en route as well as the associated “cost”.
    // * Stops are listed in stops.txt and connections (edges) between them come
    // from stop_times.txt and transfers.txt files.
    // * All lines in transfers.txt are edges (directed), while in stop_times.txt an
    // edge should be added only between 2 consecutive stops with the same trip_id.
    // * eg first 3 entries in stop_times.txt are
    // * 9017927, 5:25:00, 5:25:00,646,1,,0,0,
    // * 9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300
    // * 9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
    // * This should add a directed edge from 646 to 378, and a directed edge from
    // 378 to 379 (as they’re on the same trip id 9017927).
    // * Cost associated with edges should be as follows:
    // * 1 if it comes from stop_times.txt,
    // * 2 if it comes from transfers.txt with transfer type 0 (which is immediate
    // transfer possible),
    // * and for transfer type 2 the cost is the minimum transfer time divided by
    // 100.

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

    // prints the lines with a parent station name
    public static void printIfParent(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (line.length == 10) {
                System.out.println(Arrays.toString(line));
            }
        }
        br.close();
    }

    // to check if any of the data hsa stop url
    // spoiler there are none
    public static void printIfURL(File filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        // int count = 0;
        while ((st = br.readLine()) != null) {
            String[] line = st.split(",");
            if (!line[7].equals(" ")) {
                // if(line[7].equals(" ")){
                // System.out.println(line[7]);
                System.out.println(Arrays.toString(line));
                // count++;
            }
        }
        // System.out.println("lines without stop url are "+count);
        br.close();
    }

    /**
     * Calculate distance between two points in latitude and longitude taking into
     * account height difference. If you are not interested in height difference
     * pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters el2
     * End altitude in meters
     * 
     * @returns Distance in Meters
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public static void main(String[] args) throws IOException {
        String stops_times_path = "/Users/johnwesley/Desktop/Algos /Sem2/ADS-MOCK-TRIAL/inputs/stop_times.txt";
        File stop_times = new File(stops_times_path);

        String stops_path = "/Users/johnwesley/Desktop/Algos /Sem2/ADS-MOCK-TRIAL/inputs/stops.txt";
        File stops = new File(stops_path);

        String transfers_path = "/Users/johnwesley/Desktop/Algos /Sem2/ADS-MOCK-TRIAL/inputs/transfers.txt";
        File transfers = new File(transfers_path);

        String[] stops_column_names = getColumnNames(stops);
        // System.out.println(Arrays.toString(stops_column_names));
        // printIfParent(stops);
        // printIfURL(stops);

        double lat1 = 49.280436;
        double lon1 = -122.981419;
        double lat2 = 49.262588;
        double lon2 = -122.781242;

        double distance = getDistance(lat1, lon1, lat2, lon2);

        System.out.println("Distance between lat1,lon1 and lat2,lon2 - " + distance);
    }
}
