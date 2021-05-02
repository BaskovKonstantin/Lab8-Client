package please.help.resources.scriptExecutorService;

import java.util.ListResourceBundle;

public class ScriptExecutorServiceResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"recursionMessage1", " Стринг "},
                {"recursionMessage2",": \nПокуша да покрене рекурзију. Наставите са извршавањем скрипте?"},
                {"recursionOptions", new String [] {"Настави", "Не улази у рекурзију"}},
                {"handlingErrorMessage1", "Грешка током парсирања скрипте "},
                {"handlingErrorMessage2",": \nСтрока "},
                {"handlingErrorMessage3", "\nВе могу: \n" +
                        "- поправите погрешну линију у датотеци и наставите са извршавањем" +
                        "(све претходне линије треба да остану непромењене) \n" +
                        "- прескочите погрешну линију и наставите са извођењем \n" +
                        "- прекините извршење скрипте (наредбе које су извршене) / прекините додавање \n" +
                        "новог објекта ако је у режиму креирања (парсинг скрипте ће се наставити)"},
                {"handlingErrorOptions", new String [] {"Освежи", "Прескочи", "Прекини"}},
                {"endOfFileError", "У датотеци више нема података, али још један објект није у потпуности креиран."}
        };
    }
}
