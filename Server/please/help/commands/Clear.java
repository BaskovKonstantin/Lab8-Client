package please.help.commands;

import please.help.network.ChangeEvent;
import please.help.CollectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Класс для комманды clear.
 * Формат комманды: clear
 */
public class Clear extends Command{

    private static final long serialVersionUID = 20200916L;

    public Clear(){
        commandName = "clear";
    }

    @Override
    public String execute(CollectionManager manager, String login, String password, String locale) {
        try {
            String sql = "DELETE FROM " + manager.collectionShell.getOrgsTableName() + " WHERE owner = ?";
            PreparedStatement st = manager.collectionShell.createPreparedStatement(sql);
            if (st == null) return getStringFromResource("clearDBError", locale);
            else {
                st.setString(1, login);
                st.execute();
                st.close();

                manager.collectionShell.collection.forEach( p -> {
                    if (p.getOwner().equals(login)) manager.collectionShell.registerEvent(p, ChangeEvent.Type.DELETED);
                });
                manager.collectionShell.collection.removeIf(p -> p.getOwner().equals(login));

                return getStringFromResource("clearMessage", locale);
            }
        }
        catch (SQLException e){
            return getStringFromResource("clearDBError", locale);
        }
    }

    @Override
    public Clear validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        if (data.size() == 0 || data.poll().length > 1) {
            System.out.println("Неверно введена комманда.");
            return null;
        }
        Clear clear = new Clear();
        clear.makeValid();
        return clear;
    }
}
