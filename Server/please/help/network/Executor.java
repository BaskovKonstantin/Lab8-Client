package please.help.network;

import please.help.CollectionManager;
import please.help.commands.Change_user;
import please.help.commands.CheckEvents;
import please.help.commands.Create_new_user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.RecursiveAction;

public class Executor extends RecursiveAction {

    private final Socket client;
    private final CollectionManager manager;
    private final ClientPackage clientPackage;
    private final DataInputStream clientInput;
    private final DataOutputStream clientOutput;

    public Executor(Socket client, CollectionManager manager, ClientPackage clientPackage,
                    DataInputStream clientInput, DataOutputStream clientOutput){
        this.client = client;
        this.manager = manager;
        this.clientPackage = clientPackage;
        this.clientInput = clientInput;
        this.clientOutput = clientOutput;
    }

    @Override
    protected void compute() {
        Object feedback;
        if (manager.authorizer.authorize(clientPackage.getLogin(), clientPackage.getPassword()) ||
                clientPackage.getCommandToExecute() instanceof Change_user ||
                clientPackage.getCommandToExecute() instanceof Create_new_user) {
            Listener.logger.info("Авторизован пользователь login: {}, команда выполняется (ip: {}, port: {})"
                    , clientPackage.getLogin(), client.getInetAddress().getAddress(), client.getPort());
            feedback = clientPackage.getCommandToExecute().execute(manager,
                    clientPackage.getLogin(), clientPackage.getPassword(), clientPackage.getLocale());
            if (clientPackage.getLogin() != null && !(clientPackage.getCommandToExecute() instanceof CheckEvents))
                manager.history.addCommand(clientPackage.getCommandToExecute(), clientPackage.getLogin());
            else manager.history.addCommand(clientPackage.getCommandToExecute(), "null");
        } else {
            Listener.logger.info("Пользователь login: {} не авторизован, команда не выполнена (ip: {}, port: {})",
                    clientPackage.getLogin(), client.getInetAddress().getAddress(), client.getPort());
            feedback = "Неудачная попытка авторизации";
        }
        Listener.poolForSenders.submit(new Sender(client, feedback, clientInput, clientOutput));
    }
}
