package please.help.gui;

import please.help.commands.Create_new_user;
import please.help.commands.Show;
import please.help.network.Client;
import please.help.organizationBuilding.Organization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthForm extends JDialog {
    private JPanel contentPane;
    private JLabel header;
    private JSeparator headerSeparator;
    private JLabel loginLabel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton mainButton;
    private JButton changeModeButton;
    private JLabel changeModeLabel;
    private JLabel ipLabel;
    private JTextField ipField;
    private JTextField portField;
    private JLabel portLabel;
    private JSeparator separator1;
    private JLabel header1;
    private JPasswordField passwordCheckerField;
    private JLabel passwordCheckerLabel;
    private JTextArea errorTextArea;
    private JTextArea signUpGuide;
    private JLabel languageLabel;
    private JComboBox languageCombo;

    private final MainFrame frame;
    private AuthMode mode = AuthMode.SIGN_IN;
    private ResourceBundle resourceBundle;

    public AuthForm(MainFrame frame) {
        this.frame = frame;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(mainButton);

        errorTextArea.setVisible(false);
        passwordCheckerField.setVisible(false);
        passwordCheckerLabel.setVisible(false);
        signUpGuide.setVisible(false);

        for (Locale locale : MainFrame.getSupportedLocales()){
            languageCombo.addItem(locale.getDisplayName(locale));
        }

        languageCombo.addActionListener(e -> {
            MainFrame.currentLocale = MainFrame.getSupportedLocales()[languageCombo.getSelectedIndex()];
            frame.localize();
            localize();
        });

        mainButton.addActionListener(e -> {
            if (mode == AuthMode.SIGN_IN) onSignIn();
            else onSignUp();
        });

        changeModeButton.addActionListener(e -> {
            if (mode == AuthMode.SIGN_IN) changeToSignUp();
            else changeToSignIn();
        });

        localize();
        setTitle("AuthForm");
        setResizable(false);
        pack();
        setLocationRelativeTo(getParent());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.exitProcedure();
            }
        });
    }

    private void onSignIn() {
        if (initThings()) {
            CommandSender sender = new CommandSender(new Show(), (mainFrame, feedback) -> {
                if (feedback == null) mainFrame.getAuthForm().setVisible(false);
                else if (feedback instanceof ArrayList) {
                    if (((ArrayList<?>) feedback).size() > 0){
                        if (((ArrayList<?>) feedback).get(0) != null){
                            mainFrame.getData().setLastHash(((Organization)(((ArrayList<?>)
                                    feedback).get(0))).getCoordinates().getX());
                        }
                        ((ArrayList<?>) feedback).remove(0);
                    }
                    ((ArrayList<?>) feedback).forEach(p -> mainFrame.getData().getCollection().add((Organization) p));
                    EventQueue.invokeLater(() -> {
                        ((ArrayList<?>) feedback).forEach(p -> {
                            VisualizingPanel panel = new VisualizingPanel((Organization) p);
                            mainFrame.getOrgCircles().put(((Organization)p).getId(), panel);
                            mainFrame.addVisualizingPanel(panel);
                        });
                        mainFrame.getOrgTableModel().fireTableDataChanged();
                        mainFrame.setLoginToLabel(mainFrame.getData().getLogin());
                        if (((ArrayList<?>) feedback).size() > 0)
                            mainFrame.getData().updateFilteringFields();
                    });
                    mainFrame.startUpdate();
                    mainFrame.getAuthForm().setVisible(false);
                } else {
                    ipField.setEnabled(true);
                    portField.setEnabled(true);
                    loginField.setEnabled(true);
                    passwordField.setEnabled(true);
                    errorTextArea.setText(feedback.toString());
                    errorTextArea.setVisible(true);
                }
            });

            ipField.setEnabled(false);
            portField.setEnabled(false);
            loginField.setEnabled(false);
            passwordField.setEnabled(false);
            frame.getCommandSenderService().submit(sender);
        }
    }

    private void onSignUp(){
        if (initThings()){
            Create_new_user command = new Create_new_user();
            String validationAnswer = command.validateCommand(loginField.getText(),
                    new String(passwordField.getPassword()),
                    new String(passwordCheckerField.getPassword()));
            if (!command.isValid()){
                errorTextArea.setText(validationAnswer);
                errorTextArea.setVisible(true);
            }
            else{
                CommandSender sender = new CommandSender(command, (mainFrame, feedback) -> {
                    if (feedback instanceof String && feedback.equals("Новый пользователь создан.")) {
                        CommandSender showSender = new CommandSender(new Show(), (showFrame, showFeedback) -> {
                            if (showFeedback == null) showFrame.getAuthForm().setVisible(false);
                            else if (showFeedback instanceof ArrayList) {
                                if (((ArrayList<?>) showFeedback).size() > 0){
                                    if (((ArrayList<?>) showFeedback).get(0) != null){
                                        showFrame.getData().setLastHash(((Organization)(((ArrayList<?>)
                                                showFeedback).get(0))).getCoordinates().getX());
                                    }
                                    ((ArrayList<?>) showFeedback).remove(0);
                                }
                                ((ArrayList<?>) showFeedback).forEach(p -> showFrame.getData()
                                        .getCollection().add((Organization) p));
                                EventQueue.invokeLater(() -> {
                                    ((ArrayList<?>) showFeedback).forEach(p -> {
                                        VisualizingPanel panel = new VisualizingPanel((Organization) p);
                                        showFrame.getOrgCircles().put(((Organization)p).getId(), panel);
                                        showFrame.addVisualizingPanel(panel);
                                    });
                                    showFrame.getOrgTableModel().fireTableDataChanged();
                                    showFrame.setLoginToLabel(showFrame.getData().getLogin());
                                    if (((ArrayList<?>) showFeedback).size() > 0)
                                        showFrame.getData().updateFilteringFields();
                                });
                                showFrame.startUpdate();
                                showFrame.getAuthForm().setVisible(false);

                            } else {
                                ipField.setEnabled(true);
                                portField.setEnabled(true);
                                loginField.setEnabled(true);
                                passwordField.setEnabled(true);
                                passwordCheckerField.setEnabled(true);
                                errorTextArea.setText(showFeedback.toString());
                                errorTextArea.setVisible(true);
                            }
                        });
                        mainFrame.getCommandSenderService().submit(showSender);

                    } else {
                        ipField.setEnabled(true);
                        portField.setEnabled(true);
                        loginField.setEnabled(true);
                        passwordField.setEnabled(true);
                        passwordCheckerField.setEnabled(true);
                        errorTextArea.setText(feedback.toString());
                        errorTextArea.setVisible(true);
                    }
                });

                ipField.setEnabled(false);
                portField.setEnabled(false);
                loginField.setEnabled(false);
                passwordField.setEnabled(false);
                passwordCheckerField.setEnabled(false);
                frame.getCommandSenderService().submit(sender);
            }
        }
    }

    private boolean initThings(){
        Client client;
        try{
            client = new Client(ipField.getText(), Integer.parseInt(portField.getText()), frame);
        }
        catch (NumberFormatException e){
            errorTextArea.setText(resourceBundle.getString("portError"));
            errorTextArea.setVisible(true);
            return false;
        }

        frame.getData().setFromAuthPair(new ClientLocalData.AuthPair(loginField.getText()
                , CommandSender.getHash(new String(passwordField.getPassword()))));
        CommandSender.setClient(client);
        return true;
    }

    private void changeToSignIn() {
        errorTextArea.setVisible(false);
        passwordCheckerField.setVisible(false);
        passwordCheckerLabel.setVisible(false);
        header.setText(resourceBundle.getString("authorization"));
        changeModeLabel.setText(resourceBundle.getString("registration"));
        mainButton.setText(resourceBundle.getString("SIGN IN"));
        changeModeButton.setText(resourceBundle.getString("SIGN UP"));
        mode = AuthMode.SIGN_IN;
        signUpGuide.setVisible(false);
    }

    private void changeToSignUp(){
        errorTextArea.setVisible(false);
        passwordCheckerField.setVisible(true);
        passwordCheckerLabel.setVisible(true);
        header.setText(resourceBundle.getString("registration"));
        changeModeLabel.setText(resourceBundle.getString("authorization"));
        mainButton.setText(resourceBundle.getString("SIGN UP"));
        changeModeButton.setText(resourceBundle.getString("SIGN IN"));
        mode = AuthMode.SIGN_UP;
        signUpGuide.setVisible(true);
    }

    public void localize(){
        resourceBundle  = ResourceBundle.getBundle("please.help.resources.authForm.AuthFormResources", MainFrame.currentLocale);
        header1.setText(resourceBundle.getString("header1"));
        ipLabel.setText(resourceBundle.getString("ipLabel"));
        portLabel.setText(resourceBundle.getString("portLabel"));
        signUpGuide.setText(resourceBundle.getString("textForGuide"));
        if (mode == AuthMode.SIGN_IN){
            header.setText(resourceBundle.getString("authorization"));
            changeModeLabel.setText(resourceBundle.getString("registration"));
            mainButton.setText(resourceBundle.getString("SIGN IN"));
            changeModeButton.setText(resourceBundle.getString("SIGN UP"));
        }
        else{
            header.setText(resourceBundle.getString("registration"));
            changeModeLabel.setText(resourceBundle.getString("authorization"));
            mainButton.setText(resourceBundle.getString("SIGN UP"));
            changeModeButton.setText(resourceBundle.getString("SIGN IN"));
        }
        loginLabel.setText(resourceBundle.getString("loginLabel"));
        passwordLabel.setText(resourceBundle.getString("passwordLabel"));
        passwordCheckerLabel.setText(resourceBundle.getString("passwordCheckerLabel"));
        languageLabel.setText(resourceBundle.getString("languageLabel"));
    }

    public enum AuthMode {
        SIGN_IN, SIGN_UP
    }

    public static void main(String[] args) {
        AuthForm dialog = new AuthForm(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
