package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды clear.
 * Формат комманды: clear
 */
public class Clear extends Command{

    private static final long serialVersionUID = 20200916L;

    public Clear(){
        commandName = "clear";
    }

    @Override
    public String validateCommand(String... args) {
        makeValid();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return Add.getDefaultInfoSender(this);
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
