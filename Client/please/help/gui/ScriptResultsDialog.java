package please.help.gui;

import javax.swing.*;
import java.awt.event.*;

public class ScriptResultsDialog extends JDialog {
    private JPanel contentPane;
    private JButton okButton;
    private JTextArea textArea;

    public ScriptResultsDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        pack();

        okButton.addActionListener(e -> onOK());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });
    }

    public void setResults(String results){
        textArea.setText(results);
    }

    private void onOK() {
        textArea.setText("");
        setVisible(false);
    }

    public static void main(String[] args) {
        ScriptResultsDialog dialog = new ScriptResultsDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
