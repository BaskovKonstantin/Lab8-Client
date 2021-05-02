package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.AddDialog;
import please.help.gui.CommandSender;
import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.OrganizationBuilder;

public class Add_if_max extends Command{

    private static final long serialVersionUID = 20200916L;
    private Organization org;

    public Add_if_max(){
        commandName = "add_if_max";
    }

    private Add_if_max(Organization org){
        commandName = "add_if_max";
        this.org = org;
    }


    @Override
    public String validateCommand(String... args) {
        OrganizationBuilder builder = new OrganizationBuilder();

        String result;
        for (int i = 0; i < 7; i++){
            result = builder.addField(args[i]);
            if (result != null) return AddDialog.FIELDS_FOR_ERRORS[i] + result;
        }

        org = builder.getOrganization();
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
        org = executorService.validateOrg();
        if (org != null) makeValid();
        return null;
    }
}
