package please.help.resources.mainFrame;

import java.util.ListResourceBundle;

public class MainFrameResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"tableTab", "Табела"},
                {"visualizationTab", "Визуализација"},
                {"updateTip", "ажурирај вредност елемента колекције"},
                {"removeTip", "уклони ставку из колекције"},
                {"addMenu", "Додавање"},
                {"deleteMenu", "Брисање"},
                {"addTip", "додај нови елемент у колекцију"},
                {"addIfMaxTip", "додај нови елемент у колекцију ако је његова вредност" +
                        "прелази вредност највећег елемента" +
                        "ове колекције (сортирање по пољу годишњег промета)"},
                {"removeGreaterTip", "уклони из колекције све ставке," +
                        "већи од датог (сортирање по пољу годишњег промета)"},
                {"clearTip", "избриши све корисничке објекте"},
                {"funFactsMenu", "Статистика"},
                {"otherThingsMenu", "Остало"},
                {"historyTip", "закључи последњих 5 команди"},
                {"averageOfAnnualTurnoverTip", "закључите просечну вредност годишњег промета" +
                        "за све елементе колекције"},
                {"printFieldAscendingTypeTip", "закључите вредности поља типа свих елемената узлазним редоследом"},
                {"infoTip", "прикажи информације о колекцији"},
                {"countByAnnualTurnoverTip", "закључити број елемената," +
                        "вредност годишњег промета поља која је једнака датом"},
                {"executeScriptTip", "броји и извршава скрипту из наведене датотеке"},
                {"scriptChooserTitle", "Изаберите датотеку скрипте"},
                {"languagesMenu", "Jезик"},
                {"connectionErrorOptions1", new String [] {"Понови поптик", "Стоп"}},
                {"connectionErrorOptions2", new String [] {"Понови поптик", "Затвори програм"}},
                {"connectionErrorTitle", "Грешка везе"},
                {"connectionErrorMessage", "Не могу се повезати са сервером"},
                {"scriptErrorTitle", "Грешка скрипта"},
                {"fatalScriptErrorMessage1", "Фатална грешка током Парсирања скрипте: \n"},
                {"fatalScriptErrorMessage2", "\nСкрипт неће бити извршен"},
                {"fatalScriptErrorTitle", "Грешка скрипта"},
                {"clearMessage", "Сви ваши објекти ће бити избрисани. Слажете ли се?"},
                {"clearTitle", "Потврда брисања"},
                {"removeMessage", "Биће уклоњен објекат са ИД-ом "},
                {"removeTitle", "Потврда брисања"},
                {"countMessage1", "Унесите вредност поља годишњег промета"},
                {"countMessage2", "Погрешно унета вредност годишњег промета" +
                        "(мора се унети вредност типа double).\nпокушајте поново"},
                {"countTitle", "Бројање вредности годишњег промета"},
                {"filterLabel", "Филтрирање"},
                {"filterCheckBox", "укључено."},
                {"scriptResultsTitle", "Резултати извршења скрипте"},
                {"helpTip", "испустите помоћ за доступне команде"},
                {"helpTitle", "Доступне команде"}
        };
    }
}
