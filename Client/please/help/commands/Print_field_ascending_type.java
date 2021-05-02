package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды print_field_ascending_type.
 * Формат комманды: print_field_ascending_type
 */
public class Print_field_ascending_type extends Command{

    private static final long serialVersionUID = 20200916L;

    public Print_field_ascending_type(){
        commandName = "print_field_ascending_type";
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
                        , getStringFromResourceBundle("typeTitle")));
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
