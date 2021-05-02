package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс для комманды execute_script.
 * Формат комманды: execute_script file_name
 */
public class Execute_script extends Command{

    private static final long serialVersionUID = 20200916L;

    public Execute_script(){
        commandName = "execute_script";
    }

    @Override
    public String validateCommand(String... args) {
        Thread scriptThread = new Thread(() -> parseScript(args[0], null));
        scriptThread.setDaemon(true);
        scriptThread.start();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return null;
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        if (executorService.getCommands().size() == 0 || executorService.getCommands().peek().length != 2) {
            executorService.getCommands().poll();
            return getStringFromResourceBundle("invalidCommand");
        }

        parseScript(executorService.getCommands().poll()[1], executorService);
        return null;
    }

    public void parseScript(String path, ScriptExecutorService recursiveParent){
        ArrayList<Command> listOfCommands = new ArrayList<>();
        listOfCommands.add(new Add());
        listOfCommands.add(new Info());
        listOfCommands.add(new Update());
        listOfCommands.add(new Remove_by_id());
        listOfCommands.add(new Clear());
        listOfCommands.add(new Execute_script());
        listOfCommands.add(new History());
        listOfCommands.add(new Add_if_max());
        listOfCommands.add(new Remove_greater());
        listOfCommands.add(new Average_of_annual_turnover());
        listOfCommands.add(new Count_by_annual_turnover());
        listOfCommands.add(new Print_field_ascending_type());
        listOfCommands.add(new Show());
        listOfCommands.add(new Help());

        ScriptExecutorService executor = new ScriptExecutorService(path, recursiveParent);
        while (executor.getCommands().size() != 0 && executor.getDialogResult() != 2){
            boolean unknownCommand = true;
            if (executor.getCommands().peek() != null){
                for (Command command : listOfCommands){
                    if (command.getCommandName().equals(executor.getCommands().peek()[0])){

                        String result = command.validateForScript(executor);
                        if (result != null) executor.handleError(result);
                        else executor.setScriptSkip(executor.getScriptSkip() + 1);
                        if (command.isValid()){
                            executor.getSenders().add(new CommandSender(command, (mainFrame, feedback) ->{
                                if (feedback instanceof ArrayList){
                                    if (((ArrayList<?>) feedback).size() != 0) ((ArrayList<?>) feedback).remove(0);
                                    EventQueue.invokeLater(() -> mainFrame.getScriptResults()
                                            .get(executor.getFamilyId()).append(command.getCommandName()).append(":\n")
                                            .append(Show.createNiceTable((ArrayList<?>) feedback)).append("\n")
                                            .append("----------").append("\n\n\n"));
                                }
                                else{
                                    EventQueue.invokeLater(() -> mainFrame.getScriptResults()
                                            .get(executor.getFamilyId()).append(command.getCommandName()).append(":\n")
                                            .append(feedback.toString()).append("\n")
                                            .append("----------").append("\n\n\n"));
                                }
                            }));
                        }
                        command.makeInvalid();
                        unknownCommand = false;
                        break;
                    }
                }
                if (unknownCommand) {
                    executor.getCommands().poll();
                    executor.handleError(getStringFromResourceBundle("invalidCommand"));
                }
            }
        }
        if (recursiveParent == null) executor.sendCommands();
        else {
            executor.copyCommandsToParent();
        }
    }
}
