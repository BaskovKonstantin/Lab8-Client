package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды remove_by_id.
 * Формат комманды: remove_by_id id
 */
public class Remove_by_id extends Command{

    private static final long serialVersionUID = 20200916L;
    private Long id;

    public Remove_by_id(){
        commandName = "remove_by_id";
    }

    private Remove_by_id(Long id){
        commandName = "remove_by_id";
        this.id = id;
    }

    @Override
    public String validateCommand(String... args) {
        id = Long.parseLong(args[0]);
        makeValid();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return Add.getDefaultInfoSender(this);
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        if (executorService.getCommands().size() == 0 || executorService.getCommands().peek().length != 2) {
            executorService.getCommands().poll();
            return getStringFromResourceBundle("invalidCommand");
        }

        String[] polledCommand = executorService.getCommands().poll();
        try {
            id = Long.parseLong(polledCommand[1]);
            makeValid();
            return null;
        }
        catch (NumberFormatException e){
            return getStringFromResourceBundle("removeValidateError");
        }
    }
}
