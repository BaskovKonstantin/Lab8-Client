package please.help;

import please.help.gui.CommandSender;
import please.help.gui.MainFrame;
import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.OrganizationBuilder;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ScriptExecutorService {

    public static MainFrame frame;
    private static int id = 0;

    private int scriptSkip = 0;
    private final File script;
    private final LinkedList<String[]> commands = new LinkedList<>();
    private final ArrayList<CommandSender> senders = new ArrayList<>();
    private int dialogResult = 0;
    private final ScriptExecutorService recursiveParent;
    private final int familyId;

    private boolean paused;
    private final ReentrantLock pausedLock = new ReentrantLock();
    private final Condition unpaused = pausedLock.newCondition();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("please.help.resources" +
            ".scriptExecutorService.ScriptExecutorServiceResources", MainFrame.currentLocale);

    public ScriptExecutorService(String script, ScriptExecutorService parent){
        recursiveParent = parent;
        this.script = new File(new File(script).getAbsolutePath());
        if (parent != null){
            familyId = parent.getFamilyId();
            handleRecursion();
        }
        else{
            familyId = getNextId();
        }
        if (dialogResult != 2){
            String result = openScript(this);
            if (result != null){
                if (recursiveParent == null) {
                    frame.showFatalScriptErrorMessage(result);
                }
                else {
                    recursiveParent.handleError(result);
                    recursiveParent.setScriptSkip(recursiveParent.getScriptSkip() - 1);
                }
                dialogResult = 2;
            }
        }
    }

    public void handleRecursion(){
        ScriptExecutorService parent = recursiveParent;
        while (parent != null){
            if (parent.getScript().equals(script)){
                String message = recursiveParent.script.getName() + resourceBundle.getString("recursionMessage1")
                        + recursiveParent.getScriptSkip()
                        + resourceBundle.getString("recursionMessage2");
                String[] options = (String[]) resourceBundle.getObject("recursionOptions");
                pause();
                frame.showScriptErrorDialog(this, message, options);
                try {
                    waitForUnpaused();
                }
                catch (InterruptedException e){
                    dialogResult = 2;
                    return;
                }
                if (dialogResult != 0){
                    dialogResult = 2;
                }
                return;
            }
            parent = parent.getRecursiveParent();
        }
    }

    public boolean handleError(String error){
        String message = resourceBundle.getString("handlingErrorMessage1") + script.getName() +
                resourceBundle.getString("handlingErrorMessage2") + scriptSkip + ": " + error +
                resourceBundle.getString("handlingErrorMessage3");
        String[] options = (String[]) resourceBundle.getObject("handlingErrorOptions");
        pause();
        frame.showScriptErrorDialog(this, message, options);
        try {
            waitForUnpaused();
        }
        catch (InterruptedException e){
            dialogResult = 2;
            return false;
        }
        if (dialogResult == 0){
            String result = openScript(this);
            if (result != null){
                frame.showFatalScriptErrorMessage(result);
                dialogResult = 2;
                return false;
            }
            return true;
        }
        else if (dialogResult == 1){
            if (commands.size() != 0){
                scriptSkip++;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public Organization validateOrg(){
        OrganizationBuilder builder = new OrganizationBuilder();
        int iterator = 0;
        scriptSkip++;

        while (iterator != 7){
            if (commands.size() == 0){
                if (!handleError(resourceBundle.getString("endOfFileError"))) {
                    break;
                }
            }
            else{
                String result = builder.addField(String.join("", commands.poll()));
                if (result != null) {
                    if (!handleError(result)) break;
                }
                else{
                    scriptSkip++;
                    iterator++;
                }
            }
        }
        dialogResult = 0;
        scriptSkip--;
        return builder.getOrganization();
    }

    public void sendCommands(){
        for (CommandSender sender : senders){
            frame.getCommandSenderService().submit(sender);
        }
        frame.getCommandSenderService().submit(new CommandSender(null, (mainFrame, feedback) ->
                EventQueue.invokeLater(() -> mainFrame.showScriptResults(familyId))));
    }

    public void copyCommandsToParent(){
        for (CommandSender sender : senders){
            recursiveParent.getSenders().add(sender);
        }
    }

    public void waitForUnpaused() throws InterruptedException{
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

    public void pause(){
        pausedLock.lock();
        try{
            paused = true;
        }
        finally {
            pausedLock.unlock();
        }
    }

    public void unpause(){
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

    public File getScript() {
        return script;
    }

    public int getScriptSkip() {
        return scriptSkip;
    }

    public void setScriptSkip(int scriptSkip) {
        this.scriptSkip = scriptSkip;
    }

    public ArrayList<CommandSender> getSenders() {
        return senders;
    }

    public LinkedList<String[]> getCommands() {
        return commands;
    }

    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }

    public static void setFrame(MainFrame frame) {
        ScriptExecutorService.frame = frame;
    }

    public int getDialogResult() {
        return dialogResult;
    }

    public int getFamilyId() {
        return familyId;
    }

    private static int getNextId(){
        int oldId = id;
        id++;
        return oldId;
    }

    public static int seeNextId(){
        return id;
    }

    public ScriptExecutorService getRecursiveParent() {
        return recursiveParent;
    }

    public static String openScript(ScriptExecutorService executorService){
        String path = executorService.getScript().getAbsolutePath();
        int skip = executorService.getScriptSkip();
        LinkedList<String[]> parsedCommands = executorService.getCommands();

        File scriptFile = new File(path);
        if (!scriptFile.isFile()){
            return "Файл скрипта не обнаружен.";
        }

        String[] parsedLines;
        try(FileReader reader = new FileReader(scriptFile)){
            char[] buff = new char[500];
            StringBuilder builder = new StringBuilder();
            int c;
            while((c = reader.read(buff)) > 0){
                if (c < 500) buff = Arrays.copyOf(buff, c);
                builder.append(String.valueOf(buff));
            }
            parsedLines = builder.toString().split("\\n");
        }
        catch (IOException e) {
            return "Ошибка во время чтения файла.";
        }

        if (parsedLines.length < skip){
            return "Некорректные изменения в файле скрипта. Исполнение прервано.";
        }
        else {
            parsedCommands.clear();
            for (String str : Arrays.copyOfRange(parsedLines, skip, parsedLines.length)){
                parsedCommands.add(str.trim().split("\\s+"));
            }
            return null;
        }
    }
}
