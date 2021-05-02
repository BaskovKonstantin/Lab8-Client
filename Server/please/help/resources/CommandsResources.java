package please.help.resources;

import java.util.ListResourceBundle;

public class CommandsResources extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"orgAdded", "Элемент добавлен"},
                {"addError", "Элемент не добавлен - произошла ошибка при добавлении элемента в таблицу."},
                {"smallAddError", "Элемент не добавлен"},
                {"averageMessage", "Среднее значение поля годового оборота для всех элементов коллекции: "},
                {"clearDBError", "Элементы не удалены - ошибка при обращении к таблице."},
                {"clearMessage", "Все элементы, принадлежащие пользователю, удалены."},
                {"countMessage", "Число элементов коллекции со значением годового оборота, равным "},
                {"historyMessage", "5 последних комманд "},
                {"infoTitle", "Информация о коллекции:\n"},
                {"infoType", "Тип"},
                {"infoNumber", "Количество элементов"},
                {"infoOrgsTable", "\nТаблица с организациями"},
                {"infoUsersTable", "\nТаблица с данными пользователей"},
                {"removeIDDBError", "Элементы не удалены - ошибка при обращении к таблице."},
                {"removeIDMessage", "Элемент удален."},
                {"removeIDError", "Элемент не удален - элемент с таким id не существует " +
                        "или он не принадлежит пользователю."},
                {"removeGreaterDBError","Элементы не удалены - ошибка при обращении к таблице."},
                {"removeGreaterMessage", "Элементы удалены."},
                {"updateDBError", "Элемент не обновлен - ошибка при обращении к таблице."},
                {"updateError", "Элемент не обновлен - нет " +
                        "элемента с таким id или он не принадлежит пользователю."},
                {"updateMessage", "Элемент обновлен."}
        };
    }
}
