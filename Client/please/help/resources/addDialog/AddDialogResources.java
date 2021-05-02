package please.help.resources.addDialog;

import java.util.ListResourceBundle;

public class AddDialogResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"creationDateLabel", "Дата создания:"},
                {"ownerLabel", "Владелец:"},
                {"nameLabel", "Название  (String, не null)"},
                {"coordinatesLabel", "Координаты"},
                {"annualTurnoverLabel", "Годовой оборот (double, больше 0)"},
                {"typeLabel", "Тип организации:"},
                {"addressLabel", "Адрес"},
                {"streetLabel", "Улица (String, может быть null):"},
                {"zipCodeLabel", "Индекс (String, длина строки не меньше 4, может быть null):"},
                {"okButton", "Готово"},
                {"cancelButton", "Отмена"},
                {"FIELDS_FOR_ERRORS", new String[]{"Название: ", "Координата X: ", "Координата Y: "
                        , "Годовой оборот: ", "Тип организации: ", "Улица: ", "Индекс: "}},
                {"show", "Информация об объекте"},
                {"add", "Создание объекта"},
                {"update", "Обновление объекта"}

        };
    }
}
