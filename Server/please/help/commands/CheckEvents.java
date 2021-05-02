package please.help.commands;

import please.help.network.ChangeEvent;
import please.help.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CheckEvents extends Command{

    private static final long serialVersionUID = 20210412L;
    private final int hash;

    public CheckEvents(int hash){
        commandName = "checkEvents";
        this.hash = hash;
    }

    @Override
    public List<ChangeEvent> execute(CollectionManager manager, String login, String password, String locale) {
        ArrayList<ChangeEvent> events = new ArrayList<>();

        for (ChangeEvent event : manager.collectionShell.getChangeEvents()){
            if (event.getHash() != hash) events.add(event);
            else break;
        }
        Collections.reverse(events);

        if (events.size() == 0) return null;
        else return events;
    }

    @Override
    public Command validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        return null;
    }
}
