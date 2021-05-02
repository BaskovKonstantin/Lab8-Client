package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.AddDialog;
import please.help.gui.CommandSender;
import please.help.gui.MainFrame;
import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.OrganizationBuilder;

import java.awt.*;
import java.util.ResourceBundle;

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
        return getDefaultInfoSender(this);
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

    public static CommandSender getDefaultInfoSender(Command commandToSend){
        return new CommandSender(commandToSend, (mainFrame, feedback) -> {
            if (feedback instanceof String){
                EventQueue.invokeLater(() -> mainFrame.setCommandInfo((String) feedback));
            }
        });
    }
}
