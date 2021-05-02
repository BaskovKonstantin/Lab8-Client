package please.help.resources.client;

import java.util.ListResourceBundle;

public class ClientResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidServerData", "Неверно указаны данные для соединения с сервером"},
                {"serverDead", "Произошла ошибка при взаимодействии с сервером."},
                {"initError", "Ошибка при инициализации клиента"},
                {"connectionError", "Не удалось подключиться к серверу"}
        };
    }
}
