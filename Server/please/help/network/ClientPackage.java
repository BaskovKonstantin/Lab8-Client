package please.help.network;

import please.help.commands.Command;

import java.io.Serializable;

public class ClientPackage implements Serializable {

    private static final long serialVersionUID = 20201016L;
    private final Command commandToExecute;
    private final String login;
    private final String password;
    private final String locale;

    public ClientPackage(Command commandToExecute, String login, String password, String locale){
        this.commandToExecute = commandToExecute;
        this.login = login;
        this.password = password;
        this.locale = locale;
    }

    public Command getCommandToExecute() {
        return commandToExecute;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLocale() {
        return locale;
    }
}
