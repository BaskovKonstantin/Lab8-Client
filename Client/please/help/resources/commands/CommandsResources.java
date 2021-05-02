package please.help.resources.commands;

import java.util.ListResourceBundle;

public class CommandsResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"invalidCommand", "Неверно введена комманда."},
                {"countValidateError", "должно вводиться значение типа double."},
                {"countScriptValidateError", "Команда должна вводиться вместе со значением типа double."},
                {"emptyLoginError", "Логин не может быть пустым полем"},
                {"spacesInLoginError", "В логине не должно быть пробелов"},
                {"shortPasswordError", "Пароль должен состоять как минимум из 5 символов"},
                {"spacesInPasswordError", "В пароле не должно быть пробелов"},
                {"differentPasswordsError", "Пароли различаются"},
                {"averageTitle", "Среднее значение годового оборота"},
                {"countTitle", "Подсчет по значению годового оборота"},
                {"historyTitle", "История"},
                {"infoTitle", "Информация о коллекции"},
                {"typeTitle", "Типы объектов в порядке возрастания"},
                {"removeValidateError", "Команда должна вводиться вместе со значением типа long."},
                {"showError", "Невозможно распечатать коллекцию - некорректный ответ от сервера."},
                {"helpText", "Список доступных команд для скрипта:\n" +
                        "help : вывести справку по доступным командам\n" +
                        "info : вывести в стандартный поток вывода информацию о " +
                        "коллекции (тип, количество элементов и т.д.)\n" +
                        "show : вывести в стандартный поток вывода все элементы коллекции\n" +
                        "add {element} : добавить новый элемент в коллекцию\n" +
                        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id : удалить элемент из коллекции по его id\n" +
                        "clear : удалить из коллекции все элементы, принадлежащие пользователю\n" +
                        "execute_script file_name : считать и исполнить скрипт из указанного файла\n" +
                        "add_if_max {element} : добавить новый элемент в коллекцию, если " +
                        "его значение превышает значение наибольшего элемента " +
                        "этой коллекции (сортировка по полю annualTurnover)\n" +
                        "remove_greater {element} : удалить из коллекции все элементы, " +
                        "превышающие заданный (сортировка по полю annualTurnover)\n" +
                        "history : вывести последние 5 команд (без их аргументов)\n" +
                        "average_of_annual_turnover : вывести среднее значение поля annualTurnover" +
                        " для всех элементов коллекции\n" +
                        "count_by_annual_turnover annualTurnover : вывести количество элементов, " +
                        "значение поля annualTurnover которых равно заданному\n" +
                        "print_field_ascending_type : вывести значения поля type всех элементов в " +
                        "порядке возрастания\n"}
        };
    }
}
