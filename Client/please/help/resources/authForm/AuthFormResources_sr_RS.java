package please.help.resources.authForm;

import java.util.ListResourceBundle;

public class AuthFormResources_sr_RS extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"header1", "Сервер"},
                {"ipLabel", "ip"},
                {"portLabel", "Лука"},
                {"authorization", "Ауторизација"},
                {"registration", "Регистрација"},
                {"loginLabel", "Пријава"},
                {"passwordLabel", "Лозинка"},
                {"passwordCheckerLabel", "Понављање лозинке"},
                {"SIGN IN", "SIGN IN"},
                {"SIGN UP", "SIGN UP"},
                {"portError", "Погрешно значење порта"},
                {"textForGuide", "- Пријава не може бити празна линија, " +
                        "унутар ње не би требало бити размака\n" +
                        "- Унутар лозинке не може бити размака, дужина је најмање 5 знакова"},
                {"languageLabel", "Језик:"}
        };
    }
}
