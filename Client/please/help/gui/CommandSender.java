package please.help.gui;

import please.help.commands.Command;
import please.help.commands.Help;
import please.help.network.Client;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.BiConsumer;

public class CommandSender implements Runnable{

    private static Client client;
    private static MainFrame frame;

    private final Command commandToSend;
    private final BiConsumer<MainFrame, Object> feedbackConsumer;

    public static String getHash(String str) {
        try {
            StringBuilder hex = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-224")
                    .digest(str.getBytes())).toString(16));
            while (hex.length() < 56) hex.insert(0, '0');
            return hex.toString();
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }
    }

    public CommandSender(Command commandToSend, BiConsumer<MainFrame, Object> feedbackConsumer){
        this.commandToSend = commandToSend;
        this.feedbackConsumer = feedbackConsumer;
    }

    @Override
    public void run() {
        ClientLocalData.AuthPair authPair = frame.getData().getAuthPair();
        if (commandToSend instanceof Help){
            feedbackConsumer.accept(frame, Help.getHelpText());
        }
        else if (commandToSend != null){
            feedbackConsumer.accept(frame,
                    client.sendCommand(commandToSend, authPair.getLogin(), authPair.getPassword()));
        }
        else{
            feedbackConsumer.accept(frame, null);
        }
    }

    public static void setClient(Client client) {
        CommandSender.client = client;
    }

    public static void setFrame(MainFrame frame) {
        CommandSender.frame = frame;
    }

    public static Client getClient() {
        return client;
    }

    public static MainFrame getFrame() {
        return frame;
    }
}
