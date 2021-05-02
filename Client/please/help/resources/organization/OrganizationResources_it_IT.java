package please.help.resources.organization;

import java.util.ListResourceBundle;

public class OrganizationResources_it_IT extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"id", "id"},
                {"creationDate", "Data e ora di creazione"},
                {"name", "Titolo"},
                {"coordinateX", "Coordinata X"},
                {"coordinateY", "Coordinata Y"},
                {"annualTurnover", "Fatturato annuo"},
                {"type", "Tipo"},
                {"street", "Street"},
                {"zipCode", "Indice"},
                {"owner", "Proprietario"}
        };
    }
}
