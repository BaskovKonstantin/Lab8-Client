package please.help.resources;

import java.util.ListResourceBundle;

public class CommandsResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"orgAdded", "Елемент додан"},
                {"addError", "Елемент није додан-дошло је до грешке приликом додавања елемента у табелу."},
                {"smallAddError", "Елемент није додан"},
                {"averageMessage", "Средња вредност поља годишњег промета за све елементе колекције: "},
                {"clearDBError", "Ставке нису избрисане-грешка приликом приступа табели."},
                {"clearMessage", "Сви елементи који припадају кориснику су уклоњени."},
                {"countMessage", "Број елемената колекције са вредношћу годишњег промета једнаком "},
                {"historyMessage", "последњих 5 комманд "},
                {"infoTitle", "Информације о збирке:\n"},
                {"infoType", "Тип"},
                {"infoNumber", " број елемената"},
                {"infoOrgsTable", "\nТаблица са организацијама"},
                {"infoUsersTable", "\nТаблица са корисничким подацима"},
                {"removeIDDBError", "Ставке нису избрисане-грешка приликом приступа табели."},
                {"removeIDMessage", "Ставка је избрисана."},
                {"removeIDError", "Елемент није избрисан-елемент са таквим ИД-ом не постоји" +
                        "или не припада кориснику."},
                {"removeGreaterDBError", "Ставке нису избрисане-грешка приликом приступа табели."},
                {"removeGreaterMessage", "Ставке су уклоњене."},
                {"updateDBError", "Ставка није ажурирана-грешка приликом приступа табели."},
                {"updateError", "Елемент није ажуриран-не" +
                        "ставке са таквим ИД или не припада кориснику."},
                {"updateMessage", "Елемент је ажуриран."}
        };
    }
}
