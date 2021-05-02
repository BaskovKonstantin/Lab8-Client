package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.AddDialog;
import please.help.gui.CommandSender;
import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.OrganizationBuilder;

import java.time.LocalDateTime;

/**
 * Класс для комманды update.
 * Формат комманды: update id
 */
public class Update extends Command{

    private static final long serialVersionUID = 20200916L;
    private Long id;
    private Organization org;

    public Update(){
        commandName = "update";
    }

    private Update(Long id, Organization org){
        commandName = "update";
        this.id = id;
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
        id = Long.parseLong(args[7]);
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
            org = executorService.validateOrg();
            if (org != null) makeValid();
            return null;
        }
        catch (NumberFormatException e){
            return getStringFromResourceBundle("removeValidateError");
        }
    }
}
