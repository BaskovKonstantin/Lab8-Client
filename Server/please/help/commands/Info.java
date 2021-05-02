package please.help.commands;

import please.help.CollectionManager;

import java.util.LinkedList;


/**
 * Класс для комманды info.
 * Формат комманды: info
 */
public class Info extends Command{

    private static final long serialVersionUID = 20200916L;

    public Info(){
        commandName = "info";
    }

    @Override
    public String execute(CollectionManager manager, String login, String password, String locale) {
        return getStringFromResource("infoTitle", locale) +
                getStringFromResource("infoType", locale) + ": ConcurrentLinkedQueue<Organization>\n" +
                getStringFromResource("infoNumber", locale) + ": " + manager.collectionShell.collection.size() +
                getStringFromResource("infoOrgsTable", locale) + ": " + manager.collectionShell.getOrgsTableName() +
                getStringFromResource("infoUsersTable", locale) + ": " + manager.authorizer.getTableName();
    }

    @Override
    public Info validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        if (data.size() == 0 || data.poll().length > 1) {
            System.out.println("Неверно введена комманда.");
            return null;
        }
        Info info = new Info();
        info.makeValid();
        return info;
    }
}
