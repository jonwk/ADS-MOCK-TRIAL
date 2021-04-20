// package SearchBarGUITest;

import javax.swing.*;
// import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
public class Demo {

    JFrame frame = new JFrame("");
    // AutoCompleteDecorator decorator;
    JComboBox combobox;

    public Demo() {
        combobox = new JComboBox(new Object[]{"","Ester", "Jordi",
            "Jordina", "Jorge", "Sergi"});
        // AutoCompleteDecorator.decorate(combobox);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        frame.add(combobox);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Demo d = new Demo();
    }
}