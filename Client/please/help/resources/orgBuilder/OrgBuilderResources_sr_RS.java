package please.help.resources.orgBuilder;

import java.util.ListResourceBundle;

public class OrgBuilderResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"spaceError", "У вредностима поља не би требало бити размака."},
                {"nullFieldError", "Поље не може бити празно"},
                {"tooLongError", "Дужина стринг вредности је ограничена на 250 знакова."},
                {"maxXError", "Максимална вредност поља: 765"},
                {"maxYError", "Максимална вредност поља: 450"},
                {"annualTurnoverError", "Вредност поља треба да буде већа од 0"},
                {"shortZipCodeError", "Дужина низа треба да буде најмање 4"},
                {"finishedError", "Вредности свих поља су већ унете"},
                {"illegalArgument", "Грешка: вредност не одговара типу поља."}
        };
    }
}
