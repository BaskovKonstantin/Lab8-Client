package please.help.gui;

import please.help.commands.*;
import please.help.organizationBuilding.Organization;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class AddDialog extends JDialog {
    private JPanel contentPane;
    private JLabel header;
    private JSeparator separator1;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel coordinatesLabel;
    private JLabel coordinateXLabel;
    private JTextField coordinateXField;
    private JLabel coordinateYLabel;
    private JTextField coordinateYField;
    private JSeparator separator2;
    private JLabel annualTurnoverLabel;
    private JTextField annualTurnoverField;
    private JLabel typeLabel;
    private JComboBox typeCombo;
    private JSeparator separator3;
    private JSeparator separator4;
    private JLabel addressLabel;
    private JTextField streetField;
    private JLabel streetLabel;
    private JLabel zipCodeLabel;
    private JTextField zipCodeField;
    private JSeparator separator5;
    private JSeparator separator6;
    private JTextArea errorField;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField creationDateField;
    private JLabel creationDateLabel;
    private JSeparator separator7;
    private JLabel ownerLabel;
    private JTextField ownerField;

    private final MainFrame frame;
    private AddCommands command;
    private Long orgId;
    private ResourceBundle resourceBundle;

    public static String[] FIELDS_FOR_ERRORS;

    public AddDialog(MainFrame frame) {
        this.frame = frame;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);
        localize();

        pack();
        errorField.setVisible(false);
        idLabel.setVisible(false);
        idField.setVisible(false);
        creationDateField.setVisible(false);
        creationDateLabel.setVisible(false);
        separator7.setVisible(false);
        ownerField.setVisible(false);
        ownerLabel.setVisible(false);
        idField.setEditable(false);
        creationDateField.setEditable(false);
        ownerField.setEditable(false);
        setLocationRelativeTo(getParent());

        okButton.addActionListener(e -> onOkButton());
        cancelButton.addActionListener(e -> onCancelButton());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelButton();
            }
        });
    }

    private void onOkButton(){
        Command commandToSend;
        if (command == AddCommands.SHOW){
            onCancelButton();
            return;
        }
        if (command == AddCommands.ADD){
            commandToSend = new Add();
        }
        else if (command == AddCommands.ADD_IF_MAX){
            commandToSend = new Add_if_max();
        }
        else if (command == AddCommands.REMOVE_GREATER){
            commandToSend = new Remove_greater();
        }
        else{
            commandToSend = new Update();
        }

        String result;
        if (command != AddCommands.UPDATE){
            result = commandToSend.validateCommand(nameField.getText(), coordinateXField.getText()
                    , coordinateYField.getText(), annualTurnoverField.getText(), (String)typeCombo.getSelectedItem()
                    , streetField.getText(), zipCodeField.getText());
        }
        else{
            result = commandToSend.validateCommand(nameField.getText(), coordinateXField.getText()
                    , coordinateYField.getText(), annualTurnoverField.getText(), (String)typeCombo.getSelectedItem()
                    , streetField.getText(), zipCodeField.getText(), orgId.toString());
        }

        if (!commandToSend.isValid()){
            errorField.setText(result);
            errorField.setVisible(true);
        }
        else{
            frame.getCommandSenderService().submit(commandToSend.getCommandSender());
            onCancelButton();
        }
    }

    private void onCancelButton(){
        clearFields().setVisible(false);
    }

    public AddDialog setCommand(AddCommands command){
        if (command == AddCommands.SHOW){
            header.setText(resourceBundle.getString("show"));
            idLabel.setVisible(true);
            idField.setVisible(true);
            creationDateField.setVisible(true);
            creationDateLabel.setVisible(true);
            separator7.setVisible(true);
            cancelButton.setVisible(false);
            ownerField.setVisible(true);
            ownerLabel.setVisible(true);

            nameField.setEditable(false);
            coordinateXField.setEditable(false);
            coordinateYField.setEditable(false);
            annualTurnoverField.setEditable(false);
            streetField.setEditable(false);
            zipCodeField.setEditable(false);
            typeCombo.setEnabled(false);
        }
        else if (command != AddCommands.UPDATE){
            header.setText(resourceBundle.getString("add"));
        }
        else header.setText(resourceBundle.getString("update"));

        this.command = command;

        return this;
    }

    public AddDialog setFieldsFromOrg(Organization org){
        nameField.setText(org.getName());
        coordinateXField.setText(String.valueOf(org.getCoordinates().getX()));
        coordinateYField.setText(String.valueOf(org.getCoordinates().getY()));
        annualTurnoverField.setText(String.valueOf(org.getAnnualTurnover()));
        typeCombo.setSelectedItem((org.getType()).toString());
        streetField.setText(org.getOfficialAddress().getStreet());
        zipCodeField.setText(org.getOfficialAddress().getZipCode());
        orgId = org.getId();
        idField.setText(org.getId().toString());
        creationDateField.setText(org.getCreationDate().toString());
        ownerField.setText(org.getOwner());
        return this;
    }

    public AddDialog clearFields(){
        nameField.setText("");
        coordinateXField.setText("");
        coordinateYField.setText("");
        annualTurnoverField.setText("");
        typeCombo.setSelectedIndex(0);
        streetField.setText("");
        zipCodeField.setText("");
        errorField.setVisible(false);
        idLabel.setVisible(false);
        idField.setVisible(false);
        creationDateField.setVisible(false);
        creationDateLabel.setVisible(false);
        separator7.setVisible(false);
        cancelButton.setVisible(true);
        ownerField.setVisible(false);
        ownerLabel.setVisible(false);

        nameField.setEditable(true);
        coordinateXField.setEditable(true);
        coordinateYField.setEditable(true);
        annualTurnoverField.setEditable(true);
        streetField.setEditable(true);
        zipCodeField.setEditable(true);
        typeCombo.setEnabled(true);
        return this;
    }

    public void localize(){
        resourceBundle = ResourceBundle.getBundle("please.help.resources.addDialog.AddDialogResources"
                , MainFrame.currentLocale);
        creationDateLabel.setText(resourceBundle.getString("creationDateLabel"));
        ownerLabel.setText(resourceBundle.getString("ownerLabel"));
        nameLabel.setText(resourceBundle.getString("nameLabel"));
        coordinatesLabel.setText(resourceBundle.getString("coordinatesLabel"));
        annualTurnoverLabel.setText(resourceBundle.getString("annualTurnoverLabel"));
        typeLabel.setText(resourceBundle.getString("typeLabel"));
        addressLabel.setText(resourceBundle.getString("addressLabel"));
        streetLabel.setText(resourceBundle.getString("streetLabel"));
        zipCodeLabel.setText(resourceBundle.getString("zipCodeLabel"));
        okButton.setText(resourceBundle.getString("okButton"));
        cancelButton.setText(resourceBundle.getString("cancelButton"));
        FIELDS_FOR_ERRORS = (String[]) resourceBundle.getObject("FIELDS_FOR_ERRORS");
    }

    public enum AddCommands {
        ADD, ADD_IF_MAX, REMOVE_GREATER, UPDATE, SHOW
    }

    public static void main(String[] args) {
        AddDialog dialog = new AddDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
