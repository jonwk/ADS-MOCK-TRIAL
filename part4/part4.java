import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class part4 extends JFrame {

    private JPanel cardHolder;
    private CardLayout cards;
    private static final String cardA = "A";
    private static final String cardB = "B";
    private static final String cardC = "C";
    private static final String cardD = "D";
    private static Dimension d = new Dimension(820, 600);

    private class Switcher implements ActionListener {
        String card;

        Switcher(String card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cards.show(cardHolder, card);
        }
    }

    private JPanel makePart3Panel() {
        try {
            part3 part3 = new part3();
            // part3.Part_3_GUI();
            Map<String, ArrayList<String>> arrivalTimes_String = part3.createSortedArrivalTimeMapFromStopTimes();
            JPanel part3Panel = new JPanel();

            part3Panel.setPreferredSize(d);
            part3Panel.setMaximumSize(part3Panel.getMaximumSize());
            JButton buttonNextD = new JButton("Next");
            buttonNextD.setBounds(10, 100, 90, 20);
            JButton buttonBackD = new JButton("Back");
            buttonBackD.setBounds(600, 100, 90, 20);

            buttonNextD.addActionListener(new Switcher(cardA));
            buttonBackD.addActionListener(new Switcher(cardC));

            JButton timeInputButton = new JButton("Show");
            timeInputButton.setBackground(Color.BLACK);
            timeInputButton.setForeground(Color.RED);

            timeInputButton.setBounds(625, 300, 90, 20);

            String hours[] = { "  0", "  1", "  2", "  3", "  4", "  5", "  6", "  7", "  8", "  9", " 10", " 11",
                    " 12", " 13", " 14", " 15", " 16", " 17", " 18", " 19", " 20", " 21", " 22", " 23" };

            String minutes_seconds[] = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                    "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
                    "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
                    "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

            final JLabel hoursLabel = new JLabel();
            hoursLabel.setBounds(45, 300, 90, 20);
            hoursLabel.setText("Hours");
            final JComboBox hoursInput = new JComboBox(hours);
            hoursInput.setBounds(85, 300, 90, 20);

            final JLabel minutesLabel = new JLabel();
            minutesLabel.setBounds(235, 300, 90, 20);
            minutesLabel.setText("Minutes");
            final JComboBox minutesInput = new JComboBox(minutes_seconds);
            minutesInput.setBounds(285, 300, 90, 20);

            final JLabel secondsLabel = new JLabel();
            secondsLabel.setBounds(435, 300, 90, 20);
            secondsLabel.setText("Seconds");
            final JComboBox secondsInput = new JComboBox(minutes_seconds);
            secondsInput.setBounds(485, 300, 90, 20);

            String[] columnLabels = part3.getColumnNamesFromStopTimes();
            // String[] columnLabels = new String[10];
            String[][] tableData = new String[20][10];

            DefaultTableModel dtm = new DefaultTableModel(tableData, columnLabels);
            JTable table = new JTable(dtm);

            // taken from
            // https://www.tutorialspoint.com/how-to-set-a-tooltip-to-each-column-of-a-jtableheader-in-java
            // to get the label name when hovered on
            ToolTipHeader tooltipHeader = new ToolTipHeader(table.getColumnModel());
            tooltipHeader.setToolTipStrings(columnLabels);
            table.setTableHeader(tooltipHeader);
            // table.getTableHeader().setOpaque(false);
            // table.getTableHeader().setBackground(Color.blue);

            JTableHeader header = table.getTableHeader();
            String fg_color = "#ffffff";
            String bg_color = "#000000";
            header.setBackground(Color.decode(bg_color));
            header.setForeground(Color.decode(fg_color));

            table.setShowHorizontalLines(false);
            table.setShowVerticalLines(true);
            table.setGridColor(Color.decode(bg_color));

            JScrollPane scrollPane = new JScrollPane(table);
            // JScrollBar vScroll = scrollPane.getVerticalScrollBar();
            table.setLayout(new BorderLayout());

            int N_ROWS = tableData.length;

            Dimension td = new Dimension(800, N_ROWS * table.getRowHeight());
            table.setPreferredScrollableViewportSize(td);
            TableColumn column = null;

            for (int i = 0; i < columnLabels.length; i++) {
                column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnLabels[i].length() * 10);
            }

            for (int i = 0; i < N_ROWS; i++) {
                dtm.addRow(tableData[i]);
            }

            final JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            // label.crossVerticalAlignment(JLabel.CENTER);s
            // label.setBounds(300, -35, 100, 20);

            label.setSize(table.getWidth(), table.getHeight() * 135 / 100);
            // f.add(label,BorderLayout.CENTER);

            final JLabel count_label = new JLabel();
            // count_label.setBounds(250, -15, 100, 20);
            count_label.setHorizontalAlignment(JLabel.CENTER);
            count_label.setVerticalAlignment(JLabel.CENTER);
            count_label.setSize(table.getWidth(), table.getHeight() * 160 / 100);

            part3Panel.add(scrollPane, BorderLayout.CENTER);

            timeInputButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String time = hoursInput.getItemAt(hoursInput.getSelectedIndex()) + ":"
                            + minutesInput.getItemAt(minutesInput.getSelectedIndex()) + ":"
                            + secondsInput.getItemAt(secondsInput.getSelectedIndex());
                    String displayData = "Time you've input is " + time;
                    count_label.setText("");
                    label.setText(displayData);

                    System.out.println(displayData);

                    ArrayList<String> lines = new ArrayList<String>();
                    lines = arrivalTimes_String.get(time);
                    // printTripDetailsFromList(lines);
                    String[][] tripsData;
                    try {
                        tripsData = new String[lines.size()][columnLabels.length];
                        for (int i = 0; i < tripsData.length; i++) {
                            String line = lines.get(i);
                            String lineWithoutBraces = line.substring(1, line.length() - 1);
                            String[] values = lineWithoutBraces.split(",");
                            for (int j = 0; j < tripsData[i].length; j++) {
                                try {
                                    tripsData[i][j] = values[j];
                                } catch (ArrayIndexOutOfBoundsException aio) {
                                    tripsData[i][j] = " ";
                                    // for some lines in the file where the last elements is missing
                                }
                            }
                        }
                        int busesCount = tripsData.length;
                        count_label.setText("There seem to be " + busesCount + " buses arriving at " + time);
                        tripsData = part3.sortTripsBasedOnID(tripsData); // sorting trips based on their id using bubble
                                                                         // sort
                        // for
                        // this
                        dtm.setDataVector(tripsData, columnLabels);
                    } catch (NullPointerException np) {
                        String errorMessage = "Sorry, There doesn't seem to be any buses at the time you have selected";
                        count_label.setHorizontalAlignment(JLabel.CENTER);
                        // count_label.alignCenter();
                        count_label.setText(errorMessage);
                        System.out.println(errorMessage);
                        // dtm.setDataVector(null, columnLabels);
                        String[][] emptyData = new String[20][columnLabels.length];
                        dtm.setDataVector(emptyData, columnLabels);
                    }

                }
            });

            part3Panel.add(buttonNextD);

            part3Panel.add(hoursLabel);
            part3Panel.add(hoursInput);

            part3Panel.add(minutesLabel);
            part3Panel.add(minutesInput);

            part3Panel.add(secondsLabel);
            part3Panel.add(secondsInput);

            part3Panel.add(timeInputButton);

            part3Panel.add(buttonBackD);

            part3Panel.add(count_label);
            // pd.add(label);

            // pd.add(label4);
            return part3Panel;
        } catch (IOException ig) {
            return null;
        }

    }

    private JPanel makePart2Panel() {
        try {
            part2 part2 = new part2();
            Map<String, ArrayList<String[]>> stopDetails = part2.createNameDetailsMapFromStops();

            JPanel pc = new JPanel();

            pc.setPreferredSize(d);
            pc.setMaximumSize(pc.getMaximumSize());
            JButton buttonNextC = new JButton("Next");
            buttonNextC.setBounds(10, 100, 90, 20);
            JButton buttonBackC = new JButton("Back");
            buttonBackC.setBounds(600, 100, 90, 20);

            buttonNextC.addActionListener(new Switcher(cardD));
            buttonBackC.addActionListener(new Switcher(cardB));

            JButton inputButton = new JButton("Show");
            inputButton.setBackground(Color.BLACK);
            inputButton.setForeground(Color.RED);

            inputButton.setBounds(625, 300, 90, 20);

            String[] columnLabels = part2.getColumnNamesFromStopTimes();
            String[][] tableData = new String[20][10];

            DefaultTableModel dtm = new DefaultTableModel(tableData, columnLabels);
            JTable table = new JTable(dtm);

            // taken from
            // https://www.tutorialspoint.com/how-to-set-a-tooltip-to-each-column-of-a-jtableheader-in-java
            // to get the label name when hovered on
            ToolTipHeader tooltipHeader = new ToolTipHeader(table.getColumnModel());
            tooltipHeader.setToolTipStrings(columnLabels);
            table.setTableHeader(tooltipHeader);
            // table.getTableHeader().setOpaque(false);
            // table.getTableHeader().setBackground(Color.blue);

            JTableHeader header = table.getTableHeader();
            String fg_color = "#ffffff";
            String bg_color = "#000000";
            header.setBackground(Color.decode(bg_color));
            header.setForeground(Color.decode(fg_color));

            table.setShowHorizontalLines(false);
            table.setShowVerticalLines(true);
            table.setGridColor(Color.decode(bg_color));

            JScrollPane scrollPane = new JScrollPane(table);
            // JScrollBar vScroll = scrollPane.getVerticalScrollBar();
            table.setLayout(new BorderLayout());

            int N_ROWS = tableData.length;

            Dimension d = new Dimension(800, N_ROWS * table.getRowHeight());
            table.setPreferredScrollableViewportSize(d);
            TableColumn column = null;
            // column = table.getColumnModel().getColumn(0);
            // column.setPreferredWidth(columnLabels[0].length() * 10);
            for (int i = 0; i < columnLabels.length; i++) {
                column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnLabels[i].length() * 10);
            }

            for (int i = 0; i < N_ROWS; i++) {
                dtm.addRow(tableData[i]);
            }

            pc.add(scrollPane, BorderLayout.CENTER);

            final JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            // label.crossVerticalAlignment(JLabel.CENTER);s
            // label.setBounds(300, -35, 100, 20);

            label.setSize(table.getWidth(), table.getHeight() * 135 / 100);
            // f.add(label,BorderLayout.CENTER);

            final JLabel count_label = new JLabel();
            // count_label.setBounds(250, -15, 100, 20);
            count_label.setHorizontalAlignment(JLabel.CENTER);
            count_label.setVerticalAlignment(JLabel.CENTER);
            count_label.setSize(table.getWidth(), table.getHeight() * 160 / 100);

            JLabel errorLabel = new JLabel();
            errorLabel.setSize(table.getWidth(), table.getHeight() * 160 / 100);
            errorLabel.setHorizontalAlignment(JLabel.CENTER);
            errorLabel.setVerticalAlignment(JLabel.CENTER);

            JTextField tf1 = new JTextField(20);
            tf1.setBounds(425, 300, 150, 20);
            tf1.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent fe) {
                    if (tf1.getText().equals("Dest Stop ID")) {
                        tf1.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    if (tf1.getText().isEmpty()) {
                        tf1.setText("Search Stop Name");
                    }
                }
            });

            inputButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String input = tf1.getText();
                        Pattern p = Pattern.compile("[^a-z0-9\\- ]", Pattern.CASE_INSENSITIVE); // in order to check for
                                                                                                // an empty string input
                        Matcher m = p.matcher(input);
                        boolean b = m.find();
                        if ((!input.equals("") && !input.equals(" ") && !input.substring(0, 2).equals("  ")) && !b) {
                            String displayData = "Input String - " + input;

                            System.out.println(displayData);
                            String searchInput = part2.removeSpacesAndCapitlize(input);

                            String[] searchResults = part2.STOP_NAMES_TST.search(searchInput);
                            if (searchResults == null) {
                                String errorMessage = "Sorry, There doesn't seem to be any buses at the time you have selected";
                                count_label.setHorizontalAlignment(JLabel.CENTER);
                                // count_label.alignCenter();
                                count_label.setText(errorMessage);
                                System.out.println(errorMessage);
                                // dtm.setDataVector(null, columnLabels);
                                String[][] emptyData = new String[10][columnLabels.length];
                                dtm.setDataVector(emptyData, columnLabels);
                            }
                            ArrayList<String[]> lines = new ArrayList<>();
                            for (String res : searchResults) {
                                ArrayList<String[]> details = stopDetails.get(res);
                                for (String[] d : details) {
                                    lines.add(d);
                                }
                            }
                            // lines =
                            // printTripDetailsFromList(lines);
                            String[][] stopsData;
                            stopsData = new String[lines.size()][columnLabels.length];
                            for (int i = 0; i < stopsData.length; i++) {
                                stopsData[i] = lines.get(i);
                            }
                            int stopsCount = stopsData.length;
                            count_label.setText("\nThere seem to be " + stopsCount
                                    + " matching bus stops with the your input text");

                            dtm.setDataVector(stopsData, columnLabels);
                        } else
                            throw new IllegalArgumentException();
                    } catch (NullPointerException np) {
                        String errorMessage = "Sorry, There doesn't seem to be any bus stops that matches your input.";
                        errorLabel.setHorizontalAlignment(JLabel.CENTER);
                        // count_label.alignCenter();
                        errorLabel.setText(errorMessage);
                        // System.out.println(errorMessage);
                        // dtm.setDataVector(null, columnLabels);
                        String[][] emptyData = new String[20][columnLabels.length];
                        dtm.setDataVector(emptyData, columnLabels);
                    } catch (IllegalArgumentException ie) {
                        String errorMsg = "Please enter something valid";
                        // System.out.println("print something valid");
                        errorLabel.setText(errorMsg);
                        String[][] emptyData = new String[20][columnLabels.length];
                        dtm.setDataVector(emptyData, columnLabels);
                    }

                }
            });

            pc.add(buttonNextC);

            // pc.add(hoursLabel);
            // pc.add(hoursInput);

            // pc.add(minutesLabel);
            // pc.add(minutesInput);

            // pc.add(secondsLabel);
            pc.add(tf1);

            pc.add(inputButton);

            pc.add(buttonBackC);

            pc.add(count_label);
            pc.add(errorLabel);

            return pc;
        } catch (IOException ib) {
            return null;
        }

    }

    private void run() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setSize(55, 10);
        label1.setText("Page 1");

        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setSize(55, 10);
        label2.setText("Page 2");

        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        label3.setSize(55, 10);
        label3.setText("Page 3");

        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        label4.setSize(55, 10);
        label4.setText("Page 4");

        JPanel pa = new JPanel();
        JButton buttonNextA = new JButton("Next");
        buttonNextA.setBounds(10, 100, 90, 20);
        JButton buttonBackA = new JButton("Back");
        buttonBackA.setBounds(600, 100, 90, 20);

        buttonNextA.addActionListener(new Switcher(cardB));
        buttonBackA.addActionListener(new Switcher(cardD));
        pa.add(buttonNextA);
        pa.add(buttonBackA);
        pa.add(label1);
        pa.setBackground(Color.CYAN);
        // pa.setLayout(null);
        // pa.setSize(820, 600);

        JPanel pb = new JPanel();
        JButton buttonNextB = new JButton("Next");
        buttonNextB.setBounds(10, 100, 90, 20);
        JButton buttonBackB = new JButton("Back");
        buttonBackB.setBounds(600, 100, 90, 20);

        buttonNextB.addActionListener(new Switcher(cardC));
        buttonBackB.addActionListener(new Switcher(cardA));
        pb.add(buttonNextB);
        pb.add(buttonBackB);
        pb.add(label2);
        pb.setBackground(Color.MAGENTA);
        // pb.setLayout(null);
        // pb.setSize(820, 600);

        JPanel pc = makePart2Panel();
        // JPanel pc = new JPanel();
        // JButton buttonNextC = new JButton("Next");
        // buttonNextC.setBounds(10, 100, 90, 20);
        // JButton buttonBackC = new JButton("Back");
        // buttonBackC.setBounds(600, 100, 90, 20);

        // buttonNextC.addActionListener(new Switcher(cardD));
        // buttonBackC.addActionListener(new Switcher(cardB));
        // pc.add(buttonNextC);
        // pc.add(buttonBackC);
        // pc.setBackground(Color.YELLOW);
        // pc.add(label3);

        // pc.setLayout(null);
        // pc.setSize(820, 600);

        JPanel pd = makePart3Panel();

        // pd.setBackground(Color.WHITE);
        // pc.setLayout(null);
        // pc.setSize(820, 600);

        cardHolder = new JPanel();
        cards = new CardLayout();
        cardHolder.setLayout(cards);
        cardHolder.add(pa, cardA);
        cardHolder.add(pb, cardB);
        cardHolder.add(pc, cardC);
        cardHolder.add(pd, cardD);

        cardHolder.setPreferredSize(d);
        cardHolder.setMaximumSize(cardHolder.getPreferredSize());
        // cardHolder.setSize(820, 600);
        add(cardHolder);

        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new part4().run();
                }
            });
        } catch (Exception ex) {
        }
    }
}

// taken from
// https://www.tutorialspoint.com/how-to-set-a-tooltip-to-each-column-of-a-jtableheader-in-java
// implementation code to set a tooltip text to each column of JTableHeader
class ToolTipHeader extends JTableHeader {
    String[] toolTips;

    public ToolTipHeader(TableColumnModel model) {
        super(model);
    }

    public String getToolTipText(MouseEvent e) {
        int col = columnAtPoint(e.getPoint());
        int modelCol = getTable().convertColumnIndexToModel(col);
        String retStr;
        try {
            retStr = toolTips[modelCol];
        } catch (NullPointerException ex) {
            retStr = "";
        } catch (ArrayIndexOutOfBoundsException ex) {
            retStr = "";
        }
        if (retStr.length() < 1) {
            retStr = super.getToolTipText(e);
        }
        return retStr;
    }

    public void setToolTipStrings(String[] toolTips) {
        this.toolTips = toolTips;
    }
}
