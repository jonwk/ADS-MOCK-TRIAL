import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import java.awt.*;
import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.plaf.basic.*;

// package part2;

 // Implementtion of AutoCompleteComboBox
 public class AutoCompleteComboBox extends JComboBox {
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
       if(editor.getEditorComponent() instanceof JTextField) {
          textField = (JTextField) editor.getEditorComponent();
          textField.addKeyListener(new KeyAdapter() {
             public void keyReleased(KeyEvent ke) {
                char key = ke.getKeyChar();
                if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key) )) return;
                caretPos = textField.getCaretPosition();
                String text="";
                try {
                   text = textField.getText(0, caretPos);
                } catch (javax.swing.text.BadLocationException e) {
                   e.printStackTrace();
                }
                for (int i=0; i < getItemCount(); i++) {
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