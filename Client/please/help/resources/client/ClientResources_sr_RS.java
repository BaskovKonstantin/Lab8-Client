package please.help.resources.client;

import java.util.ListResourceBundle;

public class ClientResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidServerData", "Погрешно наведени подаци за повезивање са сервером"},
                {"serverDead", "Дошло је до грешке у интеракцији са сервером."},
                {"initError", "Грешка приликом иницијализације клијента"},
                {"connectionError", "Не могу се повезати на сервер"}
        };
    }
}
