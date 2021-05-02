package please.help.resources.orgBuilder;

import java.util.ListResourceBundle;

public class OrgBuilderResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"spaceError", "В значениях полей не должно быть пробелов."},
                {"nullFieldError", "Поле не может быть пусто"},
                {"tooLongError", "Длина строковых значений ограничена 250 символами."},
                {"maxXError", "Максимальное значение поля: 765"},
                {"maxYError", "Максимальное значение поля: 450"},
                {"annualTurnoverError", "Значение поля должно быть больше 0"},
                {"shortZipCodeError", "Длина строки должна быть не меньше 4"},
                {"finishedError", "Значения всех полей уже введены"},
                {"illegalArgument", "Ошибка: значение не соответствует типу поля."}
        };
    }
}
