package please.help.gui;

import please.help.organizationBuilding.Organization;
import please.help.organizationBuilding.OrganizationType;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.ResourceBundle;

public class OrgTableModel extends AbstractTableModel {

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int X_COORDINATE = 2;
    private static final int Y_COORDINATE = 3;
    private static final int ANNUAL_TURNOVER = 4;
    private static final int ORGANIZATION_TYPE = 5;
    private static final int STREET = 6;
    private static final int ZIP_CODE = 7;
    private static final int CREATION_DATE = 8;
    private static final int OWNER = 9;

    private String[] columnNames;

    private final ClientLocalData data;

    private NumberFormat currencyFormat;
    private DateTimeFormatter dateTimeFormatter;

    public OrgTableModel(ClientLocalData data){
        this.data = data;
        localize();
    }

    public void localize(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("please.help.resources" +
                ".orgTableModel.OrgTableModelResources", MainFrame.currentLocale);
        columnNames = (String[]) resourceBundle.getObject("columnNames");
        currencyFormat = NumberFormat.getCurrencyInstance(MainFrame.currentLocale);
        currencyFormat.setCurrency(Currency.getInstance("USD"));
        dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
                .withLocale(MainFrame.currentLocale);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.getCollection().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Organization org = data.getCollection().get(rowIndex);

        switch (columnIndex) {
            case ID:
                return org.getId();
            case NAME:
                return org.getName();
            case X_COORDINATE:
                return org.getCoordinates().getX();
            case Y_COORDINATE:
                return org.getCoordinates().getY();
            case ANNUAL_TURNOVER:
                return currencyFormat.format(org.getAnnualTurnover());
            case ORGANIZATION_TYPE:
                return org.getType();
            case STREET:
                return org.getOfficialAddress().getStreet();
            case ZIP_CODE:
                return org.getOfficialAddress().getZipCode();
            case CREATION_DATE:
                return dateTimeFormatter.format(org.getCreationDate());
            case OWNER:
                return org.getOwner();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Long.class;
            case NAME:
            case STREET:
            case ZIP_CODE:
            case OWNER:
            case ANNUAL_TURNOVER:
            case CREATION_DATE:
                return String.class;
            case X_COORDINATE:
            case Y_COORDINATE:
                return Integer.class;
            case ORGANIZATION_TYPE:
                return OrganizationType.class;
            default:
                return Object.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String[] getColumnNames() {
        return columnNames;
    }
}
