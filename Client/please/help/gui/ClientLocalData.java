package please.help.gui;

import please.help.organizationBuilding.Organization;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class ClientLocalData {

    private final List<Organization> collection = Collections.synchronizedList(new ArrayList<>());
    private final Map<Integer, List<String>> fieldsForFiltering = Collections.synchronizedMap(new HashMap<>());
    private String login;
    private String password;
    private long timestamp = 0;
    private int serverID = 0;
    private int lastHash = 0;

    {
        for (int i = 0; i < 10; i++){
            fieldsForFiltering.put(i, new ArrayList<>());
        }
    }

    public List<Organization> getCollection() {
        return collection;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public int getLastHash() {
        return lastHash;
    }

    public void setLastHash(int lastHash) {
        this.lastHash = lastHash;
    }

    public synchronized String getLogin() {
        return login;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void setLogin(String login) {
        this.login = login;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }

    public synchronized AuthPair getAuthPair(){
        return new AuthPair(login, password);
    }

    public synchronized void setFromAuthPair(AuthPair pair){
        this.login = pair.login;
        this.password = pair.password;
    }

    public static class AuthPair {

        private final String login;
        private final String password;

        public AuthPair(String login, String password){
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public void updateFilteringFields(){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(MainFrame.currentLocale);
        numberFormat.setCurrency(Currency.getInstance("USD"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT
                , FormatStyle.SHORT).withLocale(MainFrame.currentLocale);
        fieldsForFiltering.put(0, collection.stream().map(p -> p.getId().toString())
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(1, collection.stream().map(Organization::getName)
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(2, collection.stream().map(p -> String.valueOf(p.getCoordinates().getX()))
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(3, collection.stream().map(p -> String.valueOf(p.getCoordinates().getY()))
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(4, collection.stream().map(p -> numberFormat.format(p.getAnnualTurnover()))
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(5, collection.stream().map(p -> p.getType().toString())
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(6, collection.stream().map(p -> p.getOfficialAddress().getStreet())
                .distinct().collect(Collectors.toList()));
        fieldsForFiltering.put(7, collection.stream().map(p -> p.getOfficialAddress().getZipCode())
                .distinct().collect(Collectors.toList()));
        fieldsForFiltering.put(8, collection.stream().map(p -> dateTimeFormatter.format(p.getCreationDate()))
                .distinct().sorted().collect(Collectors.toList()));
        fieldsForFiltering.put(9, collection.stream().map(Organization::getOwner)
                .distinct().sorted().collect(Collectors.toList()));
    }

    public Map<Integer, List<String>> getFieldsForFiltering() {
        return fieldsForFiltering;
    }
}
