package please.help.commands;

import please.help.CollectionShell;
import please.help.network.ChangeEvent;
import please.help.CollectionManager;
import please.help.organizationBuilding.Organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CheckEvents extends Command{

    private static final long serialVersionUID = 20210412L;
    private final long timestamp;
    private final int serverID;
    private final int hash;

    public CheckEvents(long timestamp, int serverID, int hash){
        commandName = "checkEvents";
        this.timestamp = timestamp;
        this.serverID = serverID;
        this.hash = hash;
    }

    @Override
    public List<ChangeEvent> execute(CollectionManager manager, String login, String password, String locale) {
        ArrayList<ChangeEvent> events = new ArrayList<>();

        if (serverID != CollectionShell.serverID || System.currentTimeMillis() - timestamp > 30000){
            events.add(new ChangeEvent(null, ChangeEvent.Type.SHOW, CollectionShell.serverID
                    , System.currentTimeMillis()));
        }
        else{
            for (ChangeEvent event : manager.collectionShell.getChangeEvents()){
                if (event.getHash() != hash) events.add(event);
                else break;
            }
            Collections.reverse(events);
            if (events.size() == 0) events.add(new ChangeEvent(null, ChangeEvent.Type.STATUS,
                    CollectionShell.serverID, System.currentTimeMillis()));
        }
        return events;
    }

    @Override
    public Command validateCommand(LinkedList<String[]> data, CollectionManager manager) {
        return null;
    }
}
