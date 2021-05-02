package please.help.resources.organization;

import java.util.ListResourceBundle;

public class OrganizationResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"id", "id"},
                {"creationDate", "Датум и време креирања"},
                {"name", "Име"},
                {"coordinateX", "X Координата"},
                {"coordinateY", "Координата Y"},
                {"annualTurnover", "Годишњи промет"},
                {"type", "Тип"},
                {"street", "Улица"},
                {"zipCode", "Индекс"},
                {"owner", "Власник"}
        };
    }
}
