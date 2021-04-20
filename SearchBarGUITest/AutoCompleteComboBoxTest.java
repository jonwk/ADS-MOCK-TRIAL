// package part2;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.plaf.basic.*;

class AutoCompleteComboBox extends JComboBox {
   /**
    *
    */
   private static final long serialVersionUID = 1L;
   public int caretPos = 0;
   public JTextField textField = null;

   public AutoCompleteComboBox(final Object StopNames[]) {
      super(StopNames);
      setEditor(new BasicComboBoxEditor());
      setEditable(true);
   }

   public void setSelectedIndex(int index) {
      super.setSelectedIndex(index);
      textField.setText(getItemAt(index).toString());
      textField.setSelectionEnd(caretPos + textField.getText().length());
      textField.moveCaretPosition(caretPos);
   }

   public void setEditor(ComboBoxEditor editor) {
      super.setEditor(editor);
      if (editor.getEditorComponent() instanceof JTextField) {
         textField = (JTextField) editor.getEditorComponent();
         textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
               char key = ke.getKeyChar();
               if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key)))
                  return;
               caretPos = textField.getCaretPosition();
               String text = "";
               try {
                  text = textField.getText(0, caretPos);
               } catch (javax.swing.text.BadLocationException e) {
                  e.printStackTrace();
               }
               for (int i = 0; i < getItemCount(); i++) {
                  String element = (String) getItemAt(i);
                  if (element.startsWith(text)) {
                     setSelectedIndex(i);
                     return;
                  }
               }
            }
         });
      }
   }
}

public class AutoCompleteComboBoxTest extends JFrame {
   private JComboBox comboBox;

   public AutoCompleteComboBoxTest() {
      setTitle("AutoCompleteComboBox");
      String[] StopNames = new String[] { "india","indonesia", "australia", "newzealand", "england", "germany", "france", "ireland",
            "southafrica", "bangladesh", "holland", "america" };
      Arrays.sort(StopNames);
      comboBox = new AutoCompleteComboBox(StopNames);
      add(comboBox, BorderLayout.NORTH);
      setSize(400, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }

   public static void main(String[] args) {
      new AutoCompleteComboBoxTest();
   }
}
