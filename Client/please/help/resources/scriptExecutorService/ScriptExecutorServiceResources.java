package please.help.resources.scriptExecutorService;

import java.util.ListResourceBundle;

public class ScriptExecutorServiceResources extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"recursionMessage1", " Строка "},
                {"recursionMessage2", ":\nПопытка запустить рекурсию. Продолжить выполнение скрипта?"},
                {"recursionOptions", new String[]{"Продолжить", "Не входить в рекурсию"}},
                {"handlingErrorMessage1", "Ошибка во время парсинга скрипта "},
                {"handlingErrorMessage2", " :\nСтрока "},
                {"handlingErrorMessage3", "\nВы можете:\n" +
                        "- исправить некорректную строку в файле и продолжить выполнение" +
                        "(все предыдущие строки должны остаться без изменений)\n" +
                        "- пропустить некорректную строку и продолжить выполение\n" +
                        "- прервать исполнение скрипта (команды, прошедшие парсинг будут выполнены) / прервать добавление\n" +
                        "нового объекта, если в режиме создания (парсинг скрипта продолжится)"},
                {"handlingErrorOptions", new String[]{"Обновить", "Пропустить", "Прервать"}},
                {"endOfFileError", "В файле больше нет данных,но очередной объект создан не полностью."}
        };
    }
}
