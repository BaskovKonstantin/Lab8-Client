package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды average_of_annual_turnover.
 * Формат комманды: average_of_annual_turnover
 */
public class Average_of_annual_turnover extends Command{

    private static final long serialVersionUID = 20200916L;

    public Average_of_annual_turnover(){
        commandName = "average_of_annual_turnover";
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
                        , getStringFromResourceBundle("averageTitle")));
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
