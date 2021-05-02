package please.help.resources.organization;

import java.util.ListResourceBundle;

public class OrganizationResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"id", "id"},
                {"creationDate", "Дата и время создания"},
                {"name", "Название"},
                {"coordinateX", "Координата X"},
                {"coordinateY", "Координата Y"},
                {"annualTurnover", "Годовой оборот"},
                {"type", "Тип"},
                {"street", "Улица"},
                {"zipCode", "Индекс"},
                {"owner", "Владелец"}
        };
    }
}
