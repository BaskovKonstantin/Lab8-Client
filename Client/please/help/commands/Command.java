package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;
import please.help.gui.MainFrame;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Абстрактный класс для всех комманд.
 */

public abstract class Command implements Serializable {

    private static final long serialVersionUID = 20200916L;
    protected String commandName;
    private boolean valid = false;
    private static ResourceBundle resourceBundle;

    /**
     * Возвращает название комманды.
     * @return название комманды
     */
    public String getCommandName() {
        return commandName;
    }

    protected void makeValid(){
        valid = true;
    }

    protected void makeInvalid() {
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public abstract String validateCommand(String... args);

    public abstract CommandSender getCommandSender();

    public abstract String validateForScript(ScriptExecutorService executorService);

    public static synchronized String getStringFromResourceBundle(String key){
        return resourceBundle.getString(key);
    }

    public static synchronized void localize(){
        resourceBundle = ResourceBundle.getBundle("please.help.resources.commands.CommandsResources"
                , MainFrame.currentLocale);
    }
}
