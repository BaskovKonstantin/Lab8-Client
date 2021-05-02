package please.help.commands;

import please.help.ScriptExecutorService;
import please.help.gui.CommandSender;
import please.help.gui.MainFrame;
import please.help.organizationBuilding.Organization;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.ResourceBundle;

/**
 * Класс для комманды show.
 * Формат комманды: show
 */
public class Show extends Command{

    private static final long serialVersionUID = 20200916L;

    public Show(){
        commandName = "show";
    }


    @Override
    public String validateCommand(String... args) {
        makeValid();
        return null;
    }

    @Override
    public CommandSender getCommandSender() {
        return null;
    }

    @Override
    public String validateForScript(ScriptExecutorService executorService) {
        if (executorService.getCommands().size() == 0 || executorService.getCommands().poll().length > 1) {
            return getStringFromResourceBundle("invalidCommand");
        }
        makeValid();
        return null;
    }

    public static String createNiceTable(ArrayList<?> list){
        ArrayList<Organization> orgs = new ArrayList<>();

        try {
            list.forEach(p -> orgs.add((Organization) p));
        }
        catch (ClassCastException e){
            return getStringFromResourceBundle("showError");
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle("please.help.resources" +
                ".orgTableModel.OrgTableModelResources", MainFrame.currentLocale);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(MainFrame.currentLocale);
        currencyFormat.setCurrency(Currency.getInstance("USD"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT
                , FormatStyle.SHORT).withLocale(MainFrame.currentLocale);

        String[] fields = (String[]) resourceBundle.getObject("columnNames");
        int[] lengthsForPrinting = new int[10];
        StringBuilder formatter = new StringBuilder();
        StringBuilder line = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++){
            int forI = i;
            int maxFromOrgs = orgs.stream().mapToInt(p -> p.getLengthsOfFields(currencyFormat, dateTimeFormatter)[forI])
                    .max().orElse(0);
            lengthsForPrinting[i] = Math.max(maxFromOrgs, fields[i].length());
            formatter.append(" %").append(lengthsForPrinting[i]).append("s |");
            line.append(String.join("", Collections.nCopies(lengthsForPrinting[i] + 2, "-")))
                    .append("+");
        }

        result.append(String.format(formatter.toString(), fields[0], fields[1], fields[2], fields[3], fields[4]
                , fields[5], fields[6], fields[7], fields[8], fields[9])).append("\n").append(line).append("\n");

        for (Organization org : orgs){
            result.append(String.format(formatter.toString(), org.getId(), org.getName(), org.getCoordinates().getX()
                    , org.getCoordinates().getY(), dateTimeFormatter.format(org.getCreationDate())
                    , currencyFormat.format(org.getAnnualTurnover()), org.getType()
                    , org.getOfficialAddress().getStreet(), org.getOfficialAddress().getZipCode(), org.getOwner()))
                    .append("\n");
        }

        return result.toString();
    }
}
