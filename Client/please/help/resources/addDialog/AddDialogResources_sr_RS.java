package please.help.resources.addDialog;

import java.util.ListResourceBundle;

public class AddDialogResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"creationDateLabel", "Датум креирања:"},
                {"ownerLabel", "Власник:"},
                {"nameLabel", "Име (String, не null)"},
                {"coordinatesLabel", "Координате"},
                {"annualTurnoverLabel", "Годишњи промет (double, више од 0)"},
                {"typeLabel", "Тип организације:"},
                {"addressLabel", "Адреса"},
                {"streetLabel", "Улица (String, може бити null):"},
                {"zipCodeLabel", "Индекс (String дужина стринг не мање од 4, може бити null):"},
                {"okButton", "Готово"},
                {"cancelButton", "Откажи"},
                {"FIELDS_FOR_ERRORS", new String [] {"Име:", "Координата X:", "Координата Y: "
                        , "Годишњи промет:", "Тип организације:", "Улица:", "Индекс:"}},
                {"show", "Информације о објекту"},
                {"add", "Креирање објекта"},
                {"update", "Ажурирање објекта"}
        };
    }
}
