package please.help.organizationBuilding;

import please.help.gui.MainFrame;

import java.util.ResourceBundle;

public class OrganizationBuilder {

    private String name;
    private int x;
    private int y;
    private double annualTurnover;
    private OrganizationType type;
    private String street;
    private String zipCode;
    private int iterator = 0;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("please.help." +
            "resources.orgBuilder.OrgBuilderResources", MainFrame.currentLocale);

    public String addField(String unparsedField){
        try {
            if (unparsedField.trim().split("\\s+").length != 1) {
                return resourceBundle.getString("spaceError");
            } else {
                String parsedField = unparsedField.trim().split("\\s+")[0];
                if (parsedField.equals("")) parsedField = null;

                if (parsedField == null && iterator < 5) {
                    return resourceBundle.getString("nullFieldError");
                }

                if ((iterator == 0 || iterator == 5 || iterator == 6) && (parsedField != null &&
                        parsedField.length() > 250)){
                    return resourceBundle.getString("tooLongError");
                }

                if (iterator == 0) name = parsedField;

                else if (iterator == 1) {
                    int x = Integer.parseInt(parsedField);
                    if (x > 765) {
                        return resourceBundle.getString("maxXError");
                    }
                    else this.x = x;
                }

                else if (iterator == 2){
                    int y = Integer.parseInt(parsedField);
                    if (y > 450) {
                        return resourceBundle.getString("maxYError");
                    }
                    else this.y = y;
                }

                else if (iterator == 3){
                    double annualTurnover = Double.parseDouble(parsedField);
                    if (annualTurnover <= 0){
                        return resourceBundle.getString("annualTurnoverError");
                    }
                    else this.annualTurnover = annualTurnover;
                }

                else if (iterator == 4) type = OrganizationType.valueOf(parsedField);
                else if (iterator == 5) street = parsedField;
                else if (iterator == 6){
                    if (parsedField != null && parsedField.length() < 4){
                        return resourceBundle.getString("shortZipCodeError");
                    }
                    else zipCode = parsedField;
                }

                else{
                    return resourceBundle.getString("finishedError");
                }

                iterator++;
                return null;
            }
        }
        catch (IllegalArgumentException e){
            return resourceBundle.getString("illegalArgument");
        }
    }

    /**
     * Возвращает новый объект типа {@link Organization}.
     * @return {@link Organization}, если все необходимые поля инициализированы, null - иначе
     */
    public Organization getOrganization(){
        if (iterator < 6){
            return null;
        }
        else{
            Address officialAddress = new Address(street, zipCode);
            Coordinates coordinates = new Coordinates(x, y);
            return new Organization(name, coordinates, annualTurnover, type, officialAddress);
        }
    }
}
