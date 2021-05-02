package please.help.commands;

import please.help.CollectionManager;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = 20200916L;
    protected String commandName;
    private boolean valid = false;
    private static final HashMap<String, ResourceBundle> RESOURCES = new HashMap<>();
    private static final HashMap<String, NumberFormat> CURRENCY_FORMATTERS = new HashMap<>();

    static {
        RESOURCES.put("ru_RU", ResourceBundle.getBundle("please.help.resources.CommandsResources",
                new Locale("ru", "RU")));
        CURRENCY_FORMATTERS.put("ru_RU", NumberFormat.getCurrencyInstance(new Locale("ru", "RU")));
        CURRENCY_FORMATTERS.get("ru_RU").setCurrency(Currency.getInstance("USD"));
        RESOURCES.put("sr_RS", ResourceBundle.getBundle("please.help.resources.CommandsResources",
                new Locale("sr", "RS")));
        CURRENCY_FORMATTERS.put("sr_RS", NumberFormat.getCurrencyInstance(new Locale("sr", "RS")));
        CURRENCY_FORMATTERS.get("sr_RS").setCurrency(Currency.getInstance("USD"));
        RESOURCES.put("it_IT", ResourceBundle.getBundle("please.help.resources.CommandsResources",
                new Locale("it", "IT")));
        CURRENCY_FORMATTERS.put("it_IT", NumberFormat.getCurrencyInstance(new Locale("it", "IT")));
        CURRENCY_FORMATTERS.get("it_IT").setCurrency(Currency.getInstance("USD"));
        RESOURCES.put("es_NI", ResourceBundle.getBundle("please.help.resources.CommandsResources",
                new Locale("es", "NI")));
        CURRENCY_FORMATTERS.put("es_NI", NumberFormat.getCurrencyInstance(new Locale("es", "NI")));
        CURRENCY_FORMATTERS.get("es_NI").setCurrency(Currency.getInstance("USD"));
    }

    public String getCommandName() {
        return commandName;
    }

    public abstract Object execute(CollectionManager manager, String login, String password, String locale);

    protected void makeValid(){
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public abstract Command validateCommand(LinkedList<String[]> data, CollectionManager manager);

    public String getStringFromResource(String key, String locale){
        if (RESOURCES.containsKey(locale)) return RESOURCES.get(locale).getString(key);
        else return RESOURCES.get("ru_RU").getString(key);
    }

    public String formatCurrency(double value, String locale){
        if (CURRENCY_FORMATTERS.containsKey(locale)) return CURRENCY_FORMATTERS.get(locale).format(value);
        else return CURRENCY_FORMATTERS.get("ru_RU").format(value);
    }
}
