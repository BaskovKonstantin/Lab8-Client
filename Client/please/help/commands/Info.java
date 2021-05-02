package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды info.
 * Формат комманды: info
 */
public class Info extends Command{

    private static final long serialVersionUID = 20200916L;

    public Info(){
        commandName = "info";
    }

    @Override
    public String validateCommand(String... args) {
        makeValid();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return new CommandSender(this, (mainFrame, feedback) -> {
            if (feedback instanceof String)
                EventQueue.invokeLater(() -> mainFrame.showInfo((String) feedback
                        , getStringFromResourceBundle("infoTitle")));
        });
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        if (executorService.getCommands().size() == 0 || executorService.getCommands().poll().length > 1) {
            return getStringFromResourceBundle("invalidCommand");
        }
        makeValid();
        return null;
    }
}
