package please.help.commands;

import please.help.CollectionManager;

import java.util.LinkedList;

/**
 * Класс для комманды history.
 * Формат комманды: history
 */
public class History extends Command{

    private static final long serialVersionUID = 20200916L;

    public History(){
        commandName = "history";
    }

    @Override
    public String execute(CollectionManager manager, String login, String password, String locale) {
        return  getStringFromResource("historyMessage", locale) +
                login + ":\n" + manager.history.printUserCommands(login);
    }

    @Override
    public History validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        if (data.size() == 0 || data.poll().length > 1) {
            System.out.println("Неверно введена комманда.");
            return null;
        }
        History history = new History();
        history.makeValid();
        return history;
    }
}
