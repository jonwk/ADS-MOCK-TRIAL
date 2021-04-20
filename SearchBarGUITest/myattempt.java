import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.*;

// package SearchBarGUITest;

public class myattempt {
    public static void main(String[] args) {
        JFrame f = new JFrame("PART 2 GUI");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setText("label");
        label.setSize(500, 500);

        JButton button = new JButton("Search");
        button.setBounds(625, 300, 90, 20);

        f.add(button);
        f.add(label);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField(10);
        textField.setBounds(400, 300, 200, 20);

        f.add(textField);

        f.setLayout(null);
        f.setSize(820, 600);
        f.setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("text in the box is "+textField.getText());
            }
        });

        // String[] myDataBase = { "Juby", "Jaz", "Jasmine", "Joggy", "one", "dog",
        // "cat", "parot" };
        // String[] listItems;
        // JList theList = new JList();

    }

}
