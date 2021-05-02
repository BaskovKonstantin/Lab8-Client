package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;

/**
 * Класс для комманды count_by_annual_turnover.
 * Формат комманды: count_by_annual_turnover annualTurnover
 */
public class Count_by_annual_turnover extends Command{

    private double annual_turnover;
    private static final long serialVersionUID = 20200916L;

    public Count_by_annual_turnover(){
        commandName = "count_by_annual_turnover";
    }

    private Count_by_annual_turnover(double annual_turnover){
        commandName = "count_by_annual_turnover";
        this.annual_turnover = annual_turnover;
    }

    @Override
    public String validateCommand(String... args) {
        try{
            annual_turnover = Double.parseDouble(args[0]);
            makeValid();
            return null;
        }
        catch (NumberFormatException e){
            return getStringFromResourceBundle("countValidateError");
        }
    }

    @Override
    public CommandSender getCommandSender() {
        return new CommandSender(this, (mainFrame, feedback) -> {
            if (feedback instanceof String) EventQueue.invokeLater(() ->
                    mainFrame.showInfo((String)feedback
                            , getStringFromResourceBundle("countTitle")));
        });
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        if (executorService.getCommands().size() == 0 || executorService.getCommands().peek().length != 2) {
            executorService.getCommands().poll();
            return getStringFromResourceBundle("invalidCommand");
        }
        String[] polledCommand = executorService.getCommands().poll();

        try{
            annual_turnover = Double.parseDouble(polledCommand[1]);
            makeValid();
            return null;
        }
        catch (NumberFormatException e){
            return getStringFromResourceBundle("countScriptValidateError");
        }
    }
}
