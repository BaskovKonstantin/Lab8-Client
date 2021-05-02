package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

/**
 * Класс для комманды help.
 * Формат комманды: help
 */
public class Help extends Command{

    private static final long serialVersionUID = 20200916L;

    public Help(){
        commandName = "help";
    }


    public static String getHelpText() {
        return getStringFromResourceBundle("helpText");
    }

    @Override
    public String validateCommand(String... args) {
        makeValid();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return null;
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
