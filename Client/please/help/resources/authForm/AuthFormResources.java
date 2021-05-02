package please.help.resources.authForm;

import java.util.ListResourceBundle;

public class AuthFormResources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"header1", "Сервер"},
                {"ipLabel", "ip"},
                {"portLabel", "Порт"},
                {"authorization", "Авторизация"},
                {"registration", "Регистрация"},
                {"loginLabel", "Логин"},
                {"passwordLabel", "Пароль"},
                {"passwordCheckerLabel", "Повтор пароля"},
                {"SIGN IN", "SIGN IN"},
                {"SIGN UP", "SIGN UP"},
                {"portError", "Неверное значение порта"},
                {"textForGuide", "- Логин не может быть пустой строкой, внутри него не должно быть пробелов\n" +
                        "- Внутри пароля не может быть пробелов, длина - минимум 5 символов"},
                {"languageLabel", "Язык:"}
        };
    }
}
