package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

public class Create_new_user extends Command{

    private static final long serialVersionUID = 20201026L;
    private String login;
    private String password;

    public Create_new_user(){
        commandName = "create_new_user";
    }

    @Override
    public String validateCommand(String... args) {
        String login = args[0];
        String password = args[1];
        String passwordCheck = args[2];

        if (login.equals("")) return getStringFromResourceBundle("emptyLoginError");
        if (login.contains(" ")) return getStringFromResourceBundle("spacesInLoginError");
        if (password.length() < 5) return getStringFromResourceBundle("shortPasswordError");
        if (password.contains(" ")) return getStringFromResourceBundle("spacesInPasswordError");
        if (!password.equals(passwordCheck)) return getStringFromResourceBundle("differentPasswordsError");

        makeValid();
        this.login = login;
        this.password = CommandSender.getHash(password);

        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return null;
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        return null;
    }
}
