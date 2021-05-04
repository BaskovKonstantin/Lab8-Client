package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

public class CheckEvents extends Command{

    private static final long serialVersionUID = 20210412L;
    private final long timestamp;
    private final int serverID;
    private final int hash;

    public CheckEvents(long timestamp, int serverID, int hash){
        commandName = "checkEvents";
        this.timestamp = timestamp;
        this.serverID = serverID;
        this.hash = hash;
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
        return null;
    }
}
