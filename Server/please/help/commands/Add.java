package please.help.commands;

import please.help.network.ChangeEvent;
import please.help.CollectionManager;
import please.help.Mode;
import please.help.organizationBuilding.ConsoleValidator;
import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.ScriptValidator;

import java.util.LinkedList;

public class Add extends Command{

    private static final long serialVersionUID = 20200916L;
    private Organization org;

    public Add(){
        commandName = "add";
    }

    private Add(Organization org){
        commandName = "add";
        this.org = org;
    }

    @Override
    public String execute(CollectionManager manager, String login, String password, String locale) {
        if (manager.collectionShell.insertNewOrg(org, login)){
            manager.collectionShell.registerEvent(org, ChangeEvent.Type.ADDED);
            return getStringFromResource("orgAdded", locale);
        }
        else return getStringFromResource("addError", locale);
    }

    @Override
    public Add validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        if (data.size() == 0 || data.poll().length > 1) {
            System.out.println("Неверно введена комманда.");
            return null;
        }
        if (manager.getManagerMode() == Mode.CONSOLE) org = ConsoleValidator.buildFromConsole(null);
        else org = ScriptValidator.buildFromScript(null, data, manager);
        Add add = new Add(org);
        if (org != null) add.makeValid();
        return add;
    }
}
