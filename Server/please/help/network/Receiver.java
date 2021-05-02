package please.help.network;

import please.help.CollectionManager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver  implements Runnable{

    private final Socket client;
    private final CollectionManager manager;
    private final DataInputStream clientInput;
    private final DataOutputStream clientOutput;

    public Receiver(Socket client, CollectionManager manager,
                    DataInputStream clientInput, DataOutputStream clientOutput){
        this.client = client;
        this.manager = manager;
        this.clientInput = clientInput;
        this.clientOutput = clientOutput;
    }

    @Override
    public void run() {
        try {
            Listener.logger.info("Установлено соединение с клиентом: ip: {}, port: {}, начало приема команды",
                    client.getInetAddress().getAddress(), client.getPort());

            int commandLen = clientInput.readInt();
            byte[] buffer = new byte[50000];
            int counter = 0;
            while (counter != commandLen) counter += clientInput.read(buffer);
            ObjectInputStream objectInput = new ObjectInputStream(new ByteArrayInputStream(buffer));
            ClientPackage clientPackage = (ClientPackage) objectInput.readObject();
            Listener.logger.info("Получена команда {} : {} bytes (ip: {}, port: {})",
                    clientPackage.getCommandToExecute().getCommandName(), commandLen,
                    client.getInetAddress().getAddress(), client.getPort());

            Listener.poolForExecutors.execute(new Executor(client, manager, clientPackage, clientInput, clientOutput));
            objectInput.close();
        }

        catch (Exception e) {
            Listener.logger.warn("Во время приема команды произошла ошибка {} (ip: {}, port: {})", e,
                    client.getInetAddress().getAddress(), client.getPort());
        }
    }
}
