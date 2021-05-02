package please.help.network;

import please.help.commands.Command;
import please.help.gui.MainFrame;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    private static boolean paused = true;
    private static final ReentrantLock pausedLock = new ReentrantLock();
    private static final Condition unpaused = pausedLock.newCondition();
    private static boolean stop = false;
    private static ResourceBundle resourceBundle;
    private static Locale locale;

    private final String ip;
    private final int port;
    private final MainFrame frame;

    public Client(String ip, int port, MainFrame frame){
        this.frame = frame;
        this.ip = ip;
        this.port = port;
    }

    public Object sendCommand(Command command, String login, String password){
        stop = false;
        while (!stop) {
            try {
                SocketChannel socket = SocketChannel.open();
                socket.configureBlocking(false);
                Selector selector = Selector.open();
                socket.register(selector, SelectionKey.OP_CONNECT);

                try {
                    socket.connect(new InetSocketAddress(ip, port));
                    if (!(selector.select(5 * 1000) == 1 && socket.finishConnect())) throw new IOException();
                }
                catch (IllegalArgumentException e){
                    return resourceBundle.getString("invalidServerData");
                }
                catch (Exception e) {
                    waitForUserAnswer();
                    continue;
                }

                try{
                    ClientPackage packageForServer = new ClientPackage(command, login, password, locale.toString());
                    ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                    ObjectOutputStream output = new ObjectOutputStream(byteOutput);
                    output.writeObject(packageForServer);
                    output.flush();
                    ByteBuffer bufferToSend = ByteBuffer.wrap(byteOutput.toByteArray());

                    byteOutput.reset();
                    DataOutputStream dos = new DataOutputStream(byteOutput);
                    dos.writeInt(bufferToSend.capacity());
                    dos.flush();
                    ByteBuffer commandLen = ByteBuffer.wrap(byteOutput.toByteArray());
                    dos.close();
                    while (commandLen.hasRemaining()) socket.write(commandLen);
                    while (bufferToSend.hasRemaining()) socket.write(bufferToSend);

                    ByteBuffer buffer = ByteBuffer.allocate(50000);
                    long startWaiting = System.currentTimeMillis();

                    while (buffer.position() < 4) {
                        socket.read(buffer);
                        if (System.currentTimeMillis() - startWaiting > 6000){
                            waitForUserAnswer();
                            break;
                        }
                    }

                    if (stop) break;

                    buffer.flip();
                    int packageLength = buffer.getInt();
                    buffer.compact();

                    startWaiting = System.currentTimeMillis();
                    while (buffer.position() != packageLength) {
                        socket.read(buffer);
                        if (System.currentTimeMillis() - startWaiting > 6000){
                            waitForUserAnswer();
                            break;
                        }
                    }
                    if (stop) break;

                    ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
                    Object feedback = input.readObject();

                    output.close();
                    input.close();
                    socket.close();
                    selector.close();
                    return feedback;
                }
                catch (Exception e){
                    waitForUserAnswer();
                    if (stop) return resourceBundle.getString("serverDead");
                }
            }
            catch (Exception e){
                waitForUserAnswer();
                if (stop) return resourceBundle.getString("initError");
            }
        }
        return resourceBundle.getString("connectionError");
    }

    public static void waitForUnpaused() throws InterruptedException{
        if (paused){
            pausedLock.lock();
            try{
                while (paused){
                    unpaused.await();
                }
            }
            finally {
                pausedLock.unlock();
            }
        }
    }

    public static void pause(){
        pausedLock.lock();
        try{
            paused = true;
        }
        finally {
            pausedLock.unlock();
        }
    }

    public static void unpause(){
        pausedLock.lock();
        try {
            if (paused){
                paused = false;
                unpaused.signalAll();
            }
        }
        finally {
            pausedLock.unlock();
        }
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        Client.stop = stop;
    }

    private void waitForUserAnswer(){
        try {
            pause();
            EventQueue.invokeLater(frame::showConnectionErrorDialog);
            waitForUnpaused();
        }
        catch (InterruptedException e){
            stop = true;
        }
    }

    public static void localize(){
        resourceBundle = ResourceBundle.getBundle("please.help.resources.client.ClientResources"
                , MainFrame.currentLocale);
        locale = MainFrame.currentLocale;
    }
}
