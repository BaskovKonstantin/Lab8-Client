package please.help.resources.mainFrame;

import java.util.ListResourceBundle;

public class MainFrameResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"tableTab", "Таблица"},
                {"visualizationTab", "Визуализация"},
                {"updateTip", "обновить значение элемента коллекции"},
                {"removeTip", "удалить элемент из коллекции"},
                {"addMenu", "Добавление"},
                {"deleteMenu", "Удаление"},
                {"addTip", "добавить новый элемент в коллекцию"},
                {"addIfMaxTip", "добавить новый элемент в коллекцию, если его значение " +
                        "превышает значение наибольшего элемента " +
                        "этой коллекции (сортировка по полю годового оборота)"},
                {"removeGreaterTip", "удалить из коллекции все элементы, " +
                        "превышающие заданный (сортировка по полю годового оборота)"},
                {"clearTip", "удалить все объекты пользователя"},
                {"funFactsMenu", "Статистика"},
                {"otherThingsMenu", "Прочее"},
                {"historyTip", "вывести последние 5 команд"},
                {"averageOfAnnualTurnoverTip", "вывести среднее значение поля годового оборота" +
                        " для всех элементов коллекции"},
                {"printFieldAscendingTypeTip", "вывести значения поля type всех элементов в порядке возрастания"},
                {"infoTip", "вывести информацию о коллекции"},
                {"countByAnnualTurnoverTip", "вывести количество элементов," +
                        " значение поля годового оборота которых равно заданному"},
                {"executeScriptTip", "считать и исполнить скрипт из указанного файла"},
                {"scriptChooserTitle", "Выберите файл скрипта"},
                {"languagesMenu", "Язык"},
                {"connectionErrorOptions1", new String[]{"Повторить поптыку", "Остановить"}},
                {"connectionErrorOptions2", new String[]{"Повторить поптыку", "Закрыть программу"}},
                {"connectionErrorTitle", "Ошибка подключения"},
                {"connectionErrorMessage", "Не удается подключиться к серверу"},
                {"scriptErrorTitle", "Ошибка скрипта"},
                {"fatalScriptErrorMessage1", "Фатальная ошибка во время парсинга скрипта:\n"},
                {"fatalScriptErrorMessage2", "\nСкрипт не будет выполнен"},
                {"fatalScriptErrorTitle", "Ошибка скрипта"},
                {"clearMessage", "Будут удалены все ваши объекты. Вы согласны?"},
                {"clearTitle", "Подтверждение удаления"},
                {"removeMessage", "Будет удален объект с id "},
                {"removeTitle", "Подтверждение удаления"},
                {"countMessage1", "Введите значение поля годового оборота"},
                {"countMessage2", "Неверно введено значение годового оборота " +
                        "(должно вводиться значение типа double).\nПопробуйте еще раз"},
                {"countTitle", "Подсчет по значению годового оборота"},
                {"filterLabel", "Фильтрация"},
                {"filterCheckBox", "вкл."},
                {"scriptResultsTitle", "Результаты выполнения скрипта"},
                {"helpTip", "вывести справку по доступным командам"},
                {"helpTitle", "Доступные команды"}
        };
    }
}
